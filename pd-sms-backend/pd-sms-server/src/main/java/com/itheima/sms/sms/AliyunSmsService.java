package com.itheima.sms.sms;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/215:11
 */

import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.itheima.sms.entity.SignatureEntity;
import com.itheima.sms.entity.SmsConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/2 15:11
 * @updateDate 2022/3/2 15:11
 * @version 1.0
 **/

/**
 * 阿里云短信服务
 *
 * @author
 */
@Slf4j
public class AliyunSmsService extends AbstractSmsService {
    private IClientProfile profile;

    public AliyunSmsService(SmsConfig config) {
        this.config = config;
        //初始化
        init();
    }

    private void init() {
        //初始化acsClient，暂不支持region化 "cn-hangzhou"
        profile = DefaultProfile.getProfile(config.get("RegionId"), config.getAccessKeyId(), config.getAccessKeySecret());
    }

    //https://help.aliyun.com/document_detail/55284.html?spm=5176.8195934.1284193.3.65f76a7di5WyeP
    @Override
    protected String sendSms(String mobile, Map<String, String> params, String signature, String template) {
        // 获取 签名内容 和模板id
        SignatureEntity signatureEntity = signatureService.getByCode(signature);
        String code = templateService.getConfigCodeByCode(config.getId(), template);

        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        return null;
    }

    @Override
    protected String sendSmsBatch(String[] mobiles, LinkedHashMap<String, String>[] params, String[] signatures, String[] templates) {
        return null;
    }
}
