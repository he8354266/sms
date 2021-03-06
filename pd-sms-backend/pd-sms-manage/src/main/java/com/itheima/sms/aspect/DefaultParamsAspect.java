package com.itheima.sms.aspect;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/1811:11
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/18 11:11
 * @updateDate 2022/2/18 11:11     
 * @version 1.0
 **/

import com.itheima.pinda.context.BaseContextHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
 */
@Component
@Aspect
@Slf4j
public class DefaultParamsAspect {
    @SneakyThrows
    @Before("@annotation(com.itheima.sms.annotation.DefaultParams)")
    public void beforeEvent(JoinPoint point) {
        // TODO ?????????????????????????????????????????????????????????????????????????????????

        //???ThreadLocal??????????????????????????????id
        Long userId = BaseContextHandler.getUserId();
        if (userId == null) {
            userId = 0L;
        }
        //??????Controller???????????????
        Object[] args = point.getArgs();
        //????????????
        for (int i = 0; i < args.length; i++) {
            //?????????????????????????????????????????????SignatureEntity
            Object entity = args[i];
            //??????????????????,SignatureEntity.class
            Class<?> classes = entity.getClass();
            //??????????????????id??????
            //??????getId????????????
            Method method = getMethod(classes, "getId");
            if (method != null) {
                //???????????????????????????getId???
                Object id = method.invoke(entity);
                if (id == null) {
                    //??????????????????????????????????????????????????????createUser???????????????createTime
                    method = getMethod(classes, "setCreateUser", String.class);
                    if (method != null) {
                        method.invoke(entity, userId.toString());
                    }
                    method = getMethod(classes, "setCreateTime", LocalDateTime.class);
                    if (method != null) {
                        method.invoke(entity, LocalDateTime.now());
                    }
                }
                method = getMethod(classes, "setUpdateUser", String.class);
                if (method != null) {
                    method.invoke(entity, userId.toString());
                }
                method = getMethod(classes, "setUpdateTime", LocalDateTime.class);
                if (method != null) {
                    method.invoke(entity, LocalDateTime.now());
                }

            }
        }
        System.out.println(point);
    }

    /**
     * ??????????????????
     * @param classes
     * @param name
     * @param types
     * @return
     */
    private Method getMethod(Class classes, String name, Class... types) {
        try {
            return classes.getMethod(name, types);
        } catch (Exception e) {
            return null;
        }
    }

}
