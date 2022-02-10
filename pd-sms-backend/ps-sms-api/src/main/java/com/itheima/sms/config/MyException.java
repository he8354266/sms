package com.itheima.sms.config;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/917:16
 */

import lombok.Data;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/9 17:16
 * @updateDate 2022/2/9 17:16
 **/
@Data
public class MyException extends RuntimeException {
    private long code;
    private String msg;

    public MyException(Long code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
