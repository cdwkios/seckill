package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckilltable;

import java.util.Date;
import java.util.List;

/**
 * Created by jianjun-wu on 2017/2/23.
 */
public interface SeckilltableDao
{

    /**
     * 减库存
     * @param seckillID
     * @param killTime
     * @return >1 表示更新记录行数
     */
    int reduceNumber(@Param("seckillID")long seckillID,@Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckilltable queryById(Long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckilltable> queryAll(@Param("offset") int offset, @Param("limit") int limit);



}
