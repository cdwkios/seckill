package org.seckill.dto;

import org.seckill.entity.Successkilled;
import org.seckill.enums.SeckillStatEnum;

/**
 * 封装秒杀后的执行后果
 * Created by jianjun-wu on 2017/2/24.
 */
public class SeckillExecution
{

    private  long seckillId;

    //秒杀执行结果状态
    private  int state;

    //状态表示
    private String stateInfo;

    //秒杀成功对象
    private Successkilled successkilled;

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successkilled=" + successkilled +
                '}';
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, Successkilled successkilled)
    {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successkilled = successkilled;
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum)
    {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public Successkilled getSuccesskilled() {
        return successkilled;
    }

    public void setSuccesskilled(Successkilled successkilled) {
        this.successkilled = successkilled;
    }
}
