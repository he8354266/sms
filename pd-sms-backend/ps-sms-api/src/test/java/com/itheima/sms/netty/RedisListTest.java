package com.itheima.sms.netty;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/1017:40
 */

import cn.hutool.core.lang.Assert;
import com.itheima.sms.SmsApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/10 17:40
 * @updateDate 2022/2/10 17:40
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmsApiApplication.class)
public class RedisListTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testPush() {



        List<String> list = null;
        Assert.isNull(list,"list为空");
        for (int i = 0; i < 100; i++) {
            redisTemplate.opsForList().leftPush("mylist", "hello" + i);
        }
    }


    //消费
    @Test
    public void testPop() {
        while (true) {
            Object mylist = redisTemplate.opsForList().rightPop("mylist", 5l, TimeUnit.SECONDS);
            if (mylist != null) {
                System.out.println("消费消息：" + mylist);
            }
        }
    }
}
