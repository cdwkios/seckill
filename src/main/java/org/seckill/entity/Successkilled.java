package org.seckill.entity;

import java.util.Date;

/**
 * Created by jianjun-wu on 2017/2/23.
 */
public class Successkilled
{
    private long  seckillid;
    private  long userphone;
    private short state;
    private Date createtime;

    //变通
    //多对一
    private  Seckilltable seckill;

    public long getSeckillid() {
        return seckillid;
    }

    public void setSeckillid(long seckillid) {
        this.seckillid = seckillid;
    }

    public long getUserphone() {
        return userphone;
    }

    public void setUserphone(long userphone) {
        this.userphone = userphone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Seckilltable getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckilltable seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "Successkilled{" +
                "seckillid=" + seckillid +
                ", userphone=" + userphone +
                ", state=" + state +
                ", createtime=" + createtime +
                ", seckill=" + seckill +
                '}';
    }
}
