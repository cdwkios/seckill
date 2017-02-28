package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Successkilled;

/**
 * Created by jianjun-wu on 2017/2/23.
 */
public interface SucessKilledDao
{
    /**
     * 插入购买明细，可过滤重复
     * @param seckilledId
     * @param userPhone
     * @return 插入的结果集数量
     */
     int insertSuccessKilled(@Param("seckilledId") long seckilledId,@Param("userPhone") long userPhone);

    /**
     * 根据id查询 Successkilled并携带秒杀对象实体
     * @param seckillID
     * @return
     */
    Successkilled queryByIdWithSeckill(@Param("seckillID") long seckillID,@Param("userphone") long userphone);


}
