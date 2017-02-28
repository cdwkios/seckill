package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckilltable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring 和junit整合 junit启动时加载springioc容器
 * spring-test,junit
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckilltableDaoTest
{
    //注入Dao实现类依赖
     @Resource
    private SeckilltableDao seckilltableDao;


    @Test
    public void queryById() throws Exception
    {
        long id=1001;
        Seckilltable seckilltable=seckilltableDao.queryById(id);
        System.out.println(seckilltable.getSeckillName());
        System.out.println(seckilltable);
    }

    @Test
    public void queryAll() throws Exception
    {
        List<Seckilltable> seckilltables=seckilltableDao.queryAll(0,100);
        for(Seckilltable seckilltable:seckilltables)
        {
            System.out.println(seckilltable);
        }


    }

    @Test
    public void reduceNumber() throws Exception
    {
        Date killTime=new Date();
        int updatecount=seckilltableDao.reduceNumber(1000,killTime);
        System.out.println("updateCount="+updatecount);

    }


}