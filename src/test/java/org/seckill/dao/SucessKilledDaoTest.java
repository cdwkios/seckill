package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.seckill.entity.Successkilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by jianjun-wu on 2017/2/23.
 */


@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SucessKilledDaoTest {

    @Resource
    private SucessKilledDao sucessKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception
    {
        int insertCount=  sucessKilledDao.insertSuccessKilled(1001L,18583267709L);
        System.out.println("插入的行数=="+insertCount);

    }

    @Test
    public void queryByIdWithSeckill() throws Exception {

        Long id=1001L;
        Long userPhone=18583267709L;
        Successkilled successkilled=sucessKilledDao.queryByIdWithSeckill(id,userPhone);
        System.out.print(successkilled);
        System.out.print(successkilled.getSeckill());


    }

}