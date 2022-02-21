package com.itheima.sms.listener;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/1814:35
 */

import com.mysql.cj.protocol.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sun.plugin2.message.Message;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/18 14:35
 * @updateDate 2022/2/18 14:35
 **/

/**
 * 自定义消息监听器，用于监听Redis频道中的消息
 */
@Component
@Slf4j
public class MyListener implements MessageListener {
    /**
     * 监听方法
     *
     * @param message
     * @param pattern
     */
    public void onMessage(Message message, byte[] pattern) {
        log.info("接收到消息：" + message);
    }
}
