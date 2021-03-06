package com.itheima.sms.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/2110:18
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/2/21 10:18
 * @updateDate 2022/2/21 10:18     
 * @version 1.0
 **/

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.sms.dto.ConfigDTO;
import com.itheima.sms.entity.ConfigTemplateEntity;
import com.itheima.sms.mapper.ConfigTemplateMapper;
import com.itheima.sms.service.ConfigTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ??????????????????
 *
 * @author ????????????
 *
 */
@Service
@Slf4j
public class ConfigTemplateServiceImpl extends ServiceImpl<ConfigTemplateMapper, ConfigTemplateEntity> implements ConfigTemplateService {
    @Override
    public void merge(ConfigDTO entity) {
        if (!CollectionUtils.isEmpty(entity.getTemplateIds())) {
            LambdaQueryWrapper<ConfigTemplateEntity> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ConfigTemplateEntity::getConfigId, entity.getId());

            //????????????????????????
            List<ConfigTemplateEntity> dbList = this.list(wrapper);
            List<String> dbTemplateIds = dbList.stream().map(item -> item.getTemplateId()).collect(Collectors.toList());
            //??????
            List<String> deleteIds = dbTemplateIds.stream().filter(item -> !entity.getTemplateIds().contains(item)).collect(Collectors.toList());
            // ??????
            List<String> addIds = entity.getTemplateIds().stream().filter(item -> !dbTemplateIds.contains(item)).collect(Collectors.toList());

            if (!CollectionUtils.isEmpty(deleteIds)) {
                wrapper.in(ConfigTemplateEntity::getTemplateId, deleteIds);
                this.remove(wrapper);
                log.info("???????????? config:{} deleteIds:{}", entity.getId(), deleteIds);
            }
            if (!CollectionUtils.isEmpty(addIds)) {
                List<ConfigTemplateEntity> configTemplateEntities = addIds.stream().map(item -> {
                    ConfigTemplateEntity configTemplateEntity = new ConfigTemplateEntity();
                    configTemplateEntity.setConfigId(entity.getId());
                    configTemplateEntity.setTemplateId(item);
                    return configTemplateEntity;
                }).collect(Collectors.toList());
                this.saveBatch(configTemplateEntities);
                log.info("???????????? config:{} addIds:{}", entity.getId(), addIds);
            }
        }
    }
}
