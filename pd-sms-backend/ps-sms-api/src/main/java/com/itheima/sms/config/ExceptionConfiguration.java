package com.itheima.sms.config;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/917:02
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/9 17:02
 * @updateDate 2022/2/9 17:02
 * @version 1.0
 **/

import com.itheima.pinda.common.handler.DefaultGlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ??????????????????
 */
@Configuration
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionConfiguration  {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map errorHandler(Exception ex) {
        HashMap map = new HashMap();
        map.put("code", 400);
        //?????????????????????,???????????????????????????
        if (ex instanceof MissingServletRequestParameterException) {
            map.put("msg", "?????????????????????" + ((MissingServletRequestParameterException) ex).getParameterName());
        } else if (ex instanceof MyException) {
            map.put("msg", "???????????????");
        }
        return map;
    }
}
