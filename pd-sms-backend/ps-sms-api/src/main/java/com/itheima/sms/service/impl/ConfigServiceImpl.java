package com.itheima.sms.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/917:37
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.sms.entity.ConfigEntity;
import com.itheima.sms.mapper.ConfigMapper;
import com.itheima.sms.service.ConfigService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/9 17:37
 * @updateDate 2022/2/9 17:37
 **/
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, ConfigEntity> implements ConfigService {
    @Override
    public List<ConfigEntity> findByTemplateSignature(String template, String signature) {
        HashMap<Object, Object> params = new HashMap<>();
        params.put("template", template);
        params.put("signature", signature);
        return  baseMapper.findByTemplateSignature(params);
    }
}
