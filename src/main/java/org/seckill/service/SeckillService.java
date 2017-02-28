package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckilltable;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口:站在使用者的角度设计接口
 * 方法定义粒度，参数,然后类型(return 类型/异常)
 * Created by jianjun-wu on 2017/2/24.
 */
public interface SeckillService
{
    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckilltable> getSeckillList();


    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckilltable getById(long seckillId);


    /**
     * 秒杀开启输出秒杀接口地址
     * 佛则输出系统时间和秒杀时间输出秒杀接口地址
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

}
