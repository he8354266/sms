package com.itheima.sms.factory;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/111:10
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/1 11:10
 * @updateDate 2022/3/1 11:10     
 * @version 1.0
 **/

import com.alibaba.fastjson.JSON;
import com.itheima.sms.dto.SmsSendDTO;
import com.itheima.sms.entity.ManualProcessEntity;
import com.itheima.sms.entity.SendLogEntity;
import com.itheima.sms.properties.SmsProperties;
import com.itheima.sms.service.ManualProcessService;
import com.itheima.sms.service.SendLogService;
import com.itheima.sms.sms.AbstractSmsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    @Autowired
    private SmsConnectLoader smsConnectLoader;

    /**
     * 根据级别获取当前通道
     *
     * @return
     */
    @SneakyThrows
    public AbstractSmsService getSmsServiceByLevel(Integer level) {
        return smsConnectLoader.getConnectByLevel(level);
    }

    /**
     * 根据级别获取通道id
     *
     * @param level
     * @return
     */
    public String getConfigIdByLevel(Integer level) {
        AbstractSmsService connect = smsConnectLoader.getConnectByLevel(level);
        return connect.getConfig().getId();
    }

    /**
     *
     * 发送短信
     * @param deserialize
     * @return
     */
    public boolean send(String deserialize) {
        Integer level = 1;
        Integer messageErrorNum = 0;
        do {
            log.info("发送短信 level:{} , json:{}", level, deserialize);
            SendLogEntity sendLog = new SendLogEntity();
            sendLog.setCreateTime(LocalDateTime.now());
            sendLog.setUpdateTime(sendLog.getCreateTime());
            long begin = System.currentTimeMillis();
            try {
                SmsSendDTO smsSendDTO = JSON.parseObject(deserialize, SmsSendDTO.class);
                AbstractSmsService abstractSmsService = null;

                /**
                 * 当所有通道全部尝试后，如果通道级别大于所有通道配置的级别，
                 * 则说明所有通道都发送失败，该短信需要人工处理
                 */
                if (smsConnectLoader.checkConnectLevel(level)) {
                    log.warn("短信发送失败，需要人工介入处理");
                    ManualProcessEntity manualProcessEntity = new ManualProcessEntity();
                    manualProcessEntity.setMobile(smsSendDTO.getMobile());
                    manualProcessEntity.setSignature(smsSendDTO.getSignature());
                    manualProcessEntity.setTemplate(smsSendDTO.getTemplate());
                    manualProcessEntity.setConfigIds(StringUtils.join(smsSendDTO.getConfigIds()));
                    manualProcessEntity.setRequest(JSON.toJSONString(smsSendDTO.getParams()));
                    manualProcessEntity.setRequest(smsSendDTO.getSendTime());
                    manualProcessEntity.setCreateTime(LocalDateTime.now());
                    manualProcessService.save(manualProcessEntity);

                    sendLog.setConfigId("404");
                    sendLog.setConfigName("未找到");
                    sendLog.setConfigPlatform("未找到");
                    sendLog.setMobile(smsSendDTO.getMobile());
                    sendLog.setSignature(smsSendDTO.getSignature());
                    sendLog.setTemplate(smsSendDTO.getTemplate());
                    sendLog.setRequest(JSON.toJSONString(smsSendDTO));
                    sendLog.setApiLogId(smsSendDTO.getLogId());
                    sendLog.setStatus(0);
                    sendLog.setResponse("@未找到合适配置，需人工处理");
                    return false;
                }
                //根据级别获取通道id
                String configId = getConfigIdByLevel(level);
                /**
                 * 1、获取可用通道
                 *
                 * 发送短信需要模板和签名，需要和通道绑定
                 * smsSendDTO.getConfigIds()： 保存了可用模板和签名的通道列表
                 * 如果这个通道列表中包含当前通道id，则表明当前通道是可以发送这条短信的通道
                 */
            }
        } while (true);
    }
}
