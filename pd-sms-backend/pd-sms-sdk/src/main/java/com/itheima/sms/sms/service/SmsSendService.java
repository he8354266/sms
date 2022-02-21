package com.itheima.sms.sms.service;/**


 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/2114:54
 */


/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/21 14:54
 * @updateDate 2022/2/21 14:54
 **/
public interface SmsSendService {
    dto.R sendSms(dto.SmsParamsDTO smsParamsDTO);

    dto.R batchSendSms(dto.SmsBatchParamsDTO smsBatchParamsDTO);
}
