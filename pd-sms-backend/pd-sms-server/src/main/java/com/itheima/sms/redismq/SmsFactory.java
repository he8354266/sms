package com.itheima.sms.redismq;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/2310:25
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/23 10:25
 * @updateDate 2022/2/23 10:25
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/23 10:25
 * @updateDate 2022/2/23 10:25     
 * @version 1.0
 **/

import com.itheima.sms.properties.SmsProperties;
import com.itheima.sms.service.ManualProcessService;
import com.itheima.sms.service.SendLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 短信发送工厂
 1. 获取构建好的短信通道
 2. 调用通道方法，发送短信
 3. 如果发送出现异常，触发通道选举和通道降级策略
 4. 当通道选举被触发时：smsConnectLoader.buildNewConnect()
 5. 当通道降级被触发时：smsConnectLoader.changeNewConnectMessage()
 6. 记录短信发送日志
 */
@Component
@Slf4j
public class SmsFactory {
    @Autowired
    private SmsProperties smsProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SendLogService sendLogService;

    @Autowired
    private ManualProcessService manualProcessService;

//    @Autowired
//    private SmsConnectLoader smsConnectLoader;
}
