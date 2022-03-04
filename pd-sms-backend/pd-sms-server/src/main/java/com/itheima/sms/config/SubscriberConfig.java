package com.itheima.sms.config;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/416:05
 */

import com.itheima.sms.redismq.HighServerReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import javax.annotation.Resource;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/3/4 16:05
 * @updateDate 2022/3/4 16:05
 **/
@Configuration
@AutoConfigureAfter({HighServerReceiver.class})
public class SubscriberConfig {
    @Resource
    private HighServerReceiver highServerReceiver;

    /**
     * 创建消息监听容器
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);

        //可以添加多个监听订阅通道
        //当前监听的是通道：TOPIC_HIGH_SERVER
        redisMessageListenerContainer.addMessageListener(new MessageListenerAdapter(highServerReceiver), new PatternTopic("TOPIC_HIGH_SERVER"));

        return redisMessageListenerContainer;
    }
}
