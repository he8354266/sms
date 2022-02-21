package com.itheima.sms.sms.service.impl;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/2114:57
 */

import com.alibaba.fastjson.JSON;
import com.itheima.sms.sms.service.SmsSendService;
import dto.R;
import dto.SmsBatchParamsDTO;
import dto.SmsParamsDTO;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/21 14:57
 * @updateDate 2022/2/21 14:57
 **/
@Service
@Slf4j
public class SmsSendServiceImpl implements SmsSendService {
    @Value("${itheima.sms.auth}")
    private boolean auth;
    @Value("${itheima.sms.domain}")
    private String domain;
    @Value("${itheima.sms.accessKeyId}")
    private String accessKeyId;
    @Value("${itheima.sms.accessKeySecret}")
    private String accessKeySecret;

    private String send = "/sms/send";
    private String batchSend = "/sms/batchSend";

    /**
     * 单条短信
     *
     * @param smsParamsDTO
     * @return
     */
    @Override
    public R sendSms(SmsParamsDTO smsParamsDTO) {
        String url = domain + send;
//        return send(smsParamsDTO, url);
        return null;
    }

    @Override
    public R batchSendSms(SmsBatchParamsDTO smsBatchParamsDTO) {
        String url = domain + batchSend;
//        return send(smsBatchParamsDTO, url);
        return null;
    }

    /**
     * 通过HttpClient发送post请求，请求短信接收服务HTTP接口
     *
     * @param baseParamsDTO
     * @param url
     * @return
     */
    private R send(dto.BaseParamsDTO baseParamsDTO, String url) {
        //设置accessKeyId
        baseParamsDTO.setAccessKeyId(accessKeyId);
        if (auth) {
            if (StringUtils.isBlank(accessKeyId) || StringUtils.isBlank(accessKeySecret)) {
                R.fail("accessKey 不能为空");
            }
            baseParamsDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
            baseParamsDTO.setEncryption(utils.SmsEncryptionUtils.encode(baseParamsDTO.getEncryption(), baseParamsDTO.getAccessKeyId(), accessKeySecret));
        }
        if (StringUtils.isBlank(domain)) {
            R.fail("domain 不能为空");
        }
        //HTTP客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //Post请求对象
        HttpPost post = new HttpPost(url);
        //设置请求头
        post.setHeader("Content-Type", "application/json; charset=UTF-8");
        //构造请求体
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(baseParamsDTO), "UTF-8");
        post.setEntity(stringEntity);
        try {
            //发送请求
            CloseableHttpResponse response = httpClient.execute(post);
            //获得响应信息
            HttpEntity entity = response.getEntity();
            //解析响应状态码
            if (response.getStatusLine().getStatusCode() == 200) {
                log.info("httpRequest access success, StatusCode is:{}", response.getStatusLine().getStatusCode());
                String responseContent = EntityUtils.toString(entity);
                log.info("responseContent is :" + responseContent);
                return JSON.parseObject(responseContent, R.class);
            } else {
                log.error("httpRequest access fail ,StatusCode is:{}", response.getStatusLine().getStatusCode());
                return R.fail("status is " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            log.error("error :", e);
            return R.fail(e.getMessage());
        }finally {
            post.releaseConnection();
        }
    }
}
