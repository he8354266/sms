package com.itheima.sms.factory;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/2314:32
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/23 14:32
 * @updateDate 2022/2/23 14:32
 * @version 1.0
 **/

/**
 * 服务注册器，将短信发送服务注册到Redis中，定时服务上报，定时服务检查
 */
@Component
@Slf4j
@Order(value = 100)
public class ServerRegister implements CommandLineRunner {
    //当前服务实例的唯一标识，可以使用UUID随机生成
    public static String SERVER_ID = null;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 项目启动时自动执行此方法，将当前服务实例注册到redis
     *
     * @param args
     */
    @Override
    public void run(String... args) throws Exception {
        //TODO 服务注册器，项目启动时将当前服务id注册到Redis中，使用Redis的Hash结构，key为SERVER_ID_HASH，Hash结构的key为服务id，value为时间戳
        SERVER_ID = UUID.randomUUID().toString();
        log.info("生成当前服务实例id:" + SERVER_ID);

        redisTemplate.opsForHash().put("SERVER_ID_HASH", SERVER_ID, System.currentTimeMillis());
    }
}
