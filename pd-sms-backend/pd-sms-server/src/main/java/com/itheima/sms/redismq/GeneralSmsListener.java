package com.itheima.sms.redismq;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/2310:18
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/23 10:18
 * @updateDate 2022/2/23 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/23 10:18
 * @updateDate 2022/2/23 10:18
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/23 10:18
 * @updateDate 2022/2/23 10:18     
 * @version 1.0
 **/

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis队列消费者，监听消息队列TOPIC_GENERAL_SMS，普通优先级的短信，如营销短信
 */
@Component
@Slf4j
public class GeneralSmsListener extends Thread {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SmsFactory smsFactory;
}
