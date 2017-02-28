package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckilltable;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jianjun-wu on 2017/2/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
         "classpath:spring/spring-service.xml"})

public class SeckillServiceImplTest
{
    private  final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception
    {

        List<Seckilltable> list= seckillService.getSeckillList() ;
        logger.info("list={}",list);

    }

    @Test
    public void getById() throws Exception
    {
        long id=1000;
        Seckilltable seckilltable=seckillService.getById(id);
        logger.info("seckill={}",seckilltable);
    }

    @Test
    public void exportSeckillUrlTestLogin() throws Exception {

        long id=1000;

        Exposer exposer=seckillService.exportSeckillUrl(id);
        if(exposer.getExposed())
        {
            logger.info("exposer={}",exposer);

            long phone=18583267706L;
            String md5=exposer.getMd5();

            try
            {
                SeckillExecution exception=seckillService.executeSeckill(id,phone,md5);
                logger.info("result={}",exception);

            }
            catch (RepeatKillException e)
            {
                logger.error(e.getMessage());
            }
            catch (SeckillException e)
            {
                logger.error(e.getMessage());
            }

        }else
        {
             logger.warn("exposer={}",exposer);

        }

    }


    @Test
    public void executeSeckill() throws Exception
    {

        long id=1000;
        long phone=18583267707L;
        String md5="49155b50920610f77b7f8db26c521f4c";

        try
        {
            SeckillExecution exception=seckillService.executeSeckill(id,phone,md5);
            logger.info("result={}",exception);

        }
        catch (RepeatKillException e)
        {
            logger.error(e.getMessage());
        }
        catch (SeckillException e)
        {
            logger.error(e.getMessage());
        }




    }

}