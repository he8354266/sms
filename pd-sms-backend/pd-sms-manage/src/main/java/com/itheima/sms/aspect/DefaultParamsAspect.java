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
 * 通过切面方式，自定义注解，实现实体基础数据的注入（创建者、创建时间、修改者、修改时间）
 */
@Component
@Aspect
@Slf4j
public class DefaultParamsAspect {
    @SneakyThrows
    @Before("@annotation(com.itheima.sms.annotation.DefaultParams)")
    public void beforeEvent(JoinPoint point) {
        // TODO 自动注入基础属性（创建者、创建时间、修改者、修改时间）

        //从ThreadLocal中获得当前登录用户的id
        Long userId = BaseContextHandler.getUserId();
        if (userId == null) {
            userId = 0L;
        }
        //获得Controller方法的参数
        Object[] args = point.getArgs();
        //变量参数
        for (int i = 0; i < args.length; i++) {
            //对于参数，通常是实体对象，例如SignatureEntity
            Object entity = args[i];
            //获得参数类型,SignatureEntity.class
            Class<?> classes = entity.getClass();
            //获得实体中的id属性
            //获得getId方法对象
            Method method = getMethod(classes, "getId");
            if (method != null) {
                //通过反射调用方法（getId）
                Object id = method.invoke(entity);
                if (id == null) {
                    //当前进行的是新增操作，需要设置创建人createUser和创建时间createTime
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
     * 获得方法对象
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
