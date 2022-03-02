package com.itheima.sms.job;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/211:51
 */

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 11:51
 * @updateDate 2022/3/2 11:51     
 * @version 1.0
 **/

/**
 * 定时短信发送业务接口
 */
public interface SendTimingSms {
    void execute(String timing) throws InterruptedException;
}
