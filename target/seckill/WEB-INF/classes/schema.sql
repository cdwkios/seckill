-- 数据库初始化
-- 创建数据库
CREATE DATABASE seckill;

USE seckill;

CREATE  TABLE  seckilltable
(
   seckill_id BIGINT NOT NULL  AUTO_INCREMENT COMMENT  '商品库存id',
   seckill_name VARCHAR(120) NOT NULL COMMENT '商品名称',
   seckill_number INT NOT NULL COMMENT  '库存数量',
   start_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '秒杀开启时间',
   end_time  TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT  '秒杀结束时间',
   create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
   PRIMARY KEY (seckill_id),
   KEY idx_start_time(start_time),
  KEY idx_create_time(create_time),
  KEY idx_end_time(end_time)

)ENGINE =InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT ='秒杀库存表';


-- 初始化数据
INSERT  into seckilltable(seckill_name,seckill_number,start_time,end_time)
    VALUES
      ('1000秒杀iphone6',100,'2017-02-23 00:00:00','2017-02-24 00:00:00'),
      ('500秒杀iphone6',200,'2017-02-23 00:00:00','2017-02-24 00:00:00'),
      ('300秒杀iphone6',300,'2017-02-23 00:00:00','2017-02-24 00:00:00'),
      ('200秒杀iphone6',400,'2017-02-23 00:00:00','2017-02-24 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE  TABLE success_killed(
seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
user_phone BIGINT NOT NULL  COMMENT '用户手机号',
state TINYINT NOT NULL  DEFAULT -1 COMMENT '状态表示 -1无效 0 成功 1已经付款,2发货',
create_time TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone), /*联合主键*/
KEY  idx_create_time(create_time)

)ENGINE =InnoDB DEFAULT CHARSET=utf8 COMMENT ='秒杀成功明细表';


# -- 连接数据库控制台
# mysql -uroot -p