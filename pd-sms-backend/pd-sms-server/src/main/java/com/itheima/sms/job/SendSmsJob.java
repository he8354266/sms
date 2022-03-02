package com.itheima.sms.job;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/211:48
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:48
 * @updateDate 2022/3/2 11:48
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:48
 * @updateDate 2022/3/2 11:48
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:48
 * @updateDate 2022/3/2 11:48
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:48
 * @updateDate 2022/3/2 11:48
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:48
 * @updateDate 2022/3/2 11:48
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:48
 * @updateDate 2022/3/2 11:48     
 * @version 1.0
 **/

import com.itheima.sms.config.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.itheima.pinda.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务，用于发送定时短信
 */
@Component
@Slf4j
public class SendSmsJob {
    @Autowired
    private SendTimingSms sendTimingSms;

    @Autowired
    private RedisLock redisLock;

    /**
     * 每分钟检查一次是否有定时短信需要发送
     * @throws InterruptedException
     */
    //1、每分钟触发一次定时任务
    @Scheduled(cron = "10 0/1 * * * ?")//每分钟的第10秒执行1次
    public void sendTimingSms() throws InterruptedException {
        //TODO 定时任务，每分钟检查一次是否有定时短信需要发送

        //2、为了防止短信重复发送，需要使用分布式锁
        String token = redisLock.tryLock("SEND_TIMING_SMS", 1000 * 30);

        if (StringUtils.isNotBlank(token)) {
            log.info("准备执行定时发送短信任务");
            //当前实例抢到锁，可以执行定时短信的发送
            //3、调用SendTimingSmsImpl发送定时短信
            sendTimingSms.execute(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm"));
        }
    }

}
