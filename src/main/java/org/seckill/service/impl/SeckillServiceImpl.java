package org.seckill.service.impl;

import org.seckill.dao.SeckilltableDao;
import org.seckill.dao.SucessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckilltable;
import org.seckill.entity.Successkilled;
import org.seckill.enums.SeckillStatEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by jianjun-wu on 2017/2/24.
 */

@Service
public class SeckillServiceImpl implements SeckillService
{

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    //注入service依赖
    @Autowired
    private SeckilltableDao seckilltableDao;

    @Autowired
    private SucessKilledDao sucessKilledDao;

    //md5盐值
    private final String slat="asdfjasfj^&^&faw$U#*#&$123$*&$*%(*#$(@#*$&*@@#$*UJNGdjhdsf";

    public List<Seckilltable> getSeckillList()
    {
        return seckilltableDao.queryAll(0  ,4);
    }

    public Seckilltable getById(long seckillId)
    {
         return seckilltableDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId)
    {
        Seckilltable seckilltabl=seckilltableDao.queryById(seckillId);

        if(seckilltabl==null)
        {
          return new Exposer(false,seckillId);
        }

        Date startTime=seckilltabl.getStartTime();
        Date endTime=seckilltabl.getEndTime();

        //系统时间
        Date nowTime=new Date();

        if(nowTime.getTime()< startTime.getTime()
                ||nowTime.getTime()>endTime.getTime())
        {
            return  new Exposer(false,seckillId,nowTime.getTime(),
                    startTime.getTime(),endTime.getTime());

        }

        //转化特定字符串的过程，不可逆
        String md5=getMD5(seckillId);
        return new Exposer(true,md5,seckillId);

    }

    private String getMD5(long seckillId)
    {
        String base=seckillId+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;

    }

    @Transactional
    /**
     * 使用注解控制事务优点
     * 1，开发团队达成一致约定，明确事务方法的编程风格
     * 2，保证事务执行时间尽可能短，不要穿插其他网络操作PRC/http请求
     * 3，不是所有的方法都需要事务,只有一条修改操作，只读操作需要
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException {

      if(md5==null||!md5.equals(getMD5(seckillId)))
      {
           throw  new SeckillException("seckill data rewrite");
      }

      //执行秒杀逻辑：减库存+记录购买行为
        Date nowTime=new Date();

      try
      {
          int upDateCount=seckilltableDao.reduceNumber(seckillId,nowTime);
          if(upDateCount<=0)
          {
              //没有更新到记录，秒杀结束
              throw  new SeckillCloseException("seckill is closed");
          }
          else
          {
              //记录购买行为
              int insertCount=sucessKilledDao.insertSuccessKilled(seckillId,userPhone);
              //唯一:seckillId,userPhone
              if(insertCount<=0)
              {
                  throw  new  RepeatKillException("seckill repeated");

              }
              else
              {
                  Successkilled successkilled=sucessKilledDao.queryByIdWithSeckill(seckillId,userPhone);
                  return  new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS);
              }

          }

      }
      catch (SeckillCloseException e1)
      {
           throw  e1;

      }
      catch (RepeatKillException e2)
      {
          throw  e2;

      }
      catch (Exception e)
      {

          //rollBack
          logger.error(e.getMessage(),e);
          throw  new SeckillException("seckill inner error:"+e.getMessage());

      }





    }
}
