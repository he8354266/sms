package com.itheima.sms.job;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/211:52
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:52
 * @updateDate 2022/3/2 11:52     
 * @version 1.0
 **/

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.sms.entity.TimingPushEntity;
import com.itheima.sms.factory.SmsFactory;
import com.itheima.sms.mapper.TimingPushMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时短信业务处理器
 */
@Component
@Slf4j
public class SendTimingSmsImpl implements SendTimingSms {
    @Autowired
    private TimingPushMapper timingPushMapper;

    @Autowired
    private SmsFactory smsFactory;

    /**
     * 发送定时短信
     * @param timing
     */
    @Override
    public void execute(String timing) throws InterruptedException {
        //TODO 查询数据库获取本次需要发送的定时短信，调用短信工厂发送短信

        //1、查询数据库获取本次需要发送的定时短信
        LambdaQueryWrapper<TimingPushEntity> queryWrapper = new LambdaQueryWrapper<>();
        //设置查询条件，状态为0，表示未处理
        queryWrapper.eq(TimingPushEntity::getStatus, 0);
        //设置查询条件，发送时间等值查询
        queryWrapper.eq(TimingPushEntity::getTiming, timing);
        //设置排序条件
        queryWrapper.orderByAsc(TimingPushEntity::getCreateTime);
        List<TimingPushEntity> list = timingPushMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0) {
            log.info("本次定时任务需要发送的定时短信条数为：{}", list.size());
            list.forEach(timingPushEntity -> {
                //调用短信工厂发送短信
                smsFactory.send(timingPushEntity.getRequest());

                //3、更新短信发送状态为“已处理”
                timingPushEntity.setStatus(1);
                timingPushMapper.updateById(timingPushEntity);
            });
        }
    }

}
