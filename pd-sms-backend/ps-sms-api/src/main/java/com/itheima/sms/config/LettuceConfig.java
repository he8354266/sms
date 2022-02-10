package com.itheima.sms.config;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/817:03
 */

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/8 17:03
 * @updateDate 2022/2/8 17:03
 **/
public class LettuceConfig extends CachingConfigurerSupport {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //redisTemplate.setKeySerializer(new StringRedisSerializer()); //key序列化
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());//value序列化
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());//Hash key序列化
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());//Hash value序列化
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
