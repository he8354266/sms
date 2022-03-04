package com.itheima.sms.sms;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/314:20
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20
 * @version 1.0
 **/

/**
 * @description zkjy
 * @author zkjy
 * @updateUser
 * @createDate 2022/3/3 14:20
 * @updateDate 2022/3/3 14:20     
 * @version 1.0
 **/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.sms.entity.SmsConfig;
import io.undertow.util.StatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 飞鸽
 * http://sms.feige.ee/
 */
@Slf4j
public class FeigeSmsService extends AbstractSmsService {
    public FeigeSmsService(SmsConfig config) {
        this.config = config;
    }

    @Override
    protected String sendSms(String mobile, Map<String, String> params, String signature, String template) {
        // 获取 签名内容 和模板id
        String signatureCode = signatureService.getConfigCodeByCode(config.getId(), signature);
        String templateCode = templateService.getConfigCodeByCode(config.getId(), template);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = new HttpPost(config.getDomain());
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            StringBuffer contentSbf = new StringBuffer();
            for (String value : params.values()) {
                contentSbf.append(value).append("||");
            }
            if (contentSbf.length() >= 2) {
                contentSbf.deleteCharAt(contentSbf.length() - 1);
                contentSbf.deleteCharAt(contentSbf.length() - 1);
            }
            List postParams = new ArrayList<>();
            postParams.add(new BasicNameValuePair("Account", config.getAccessKeyId()));
            postParams.add(new BasicNameValuePair("Pwd", config.getAccessKeySecret()));
            postParams.add(new BasicNameValuePair("Content", contentSbf.toString()));
            postParams.add(new BasicNameValuePair("Mobile", mobile));
            postParams.add(new BasicNameValuePair("TemplateId", templateCode));
            postParams.add(new BasicNameValuePair("SignId", signatureCode));

            HttpEntity httpEntity = new UrlEncodedFormEntity(postParams, "UTF-8");
            post.setEntity(httpEntity);

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();

            if (response.getStatusLine().getStatusCode() == StatusCodes.OK) {
                log.info("httpRequest access success, StatusCode is:{}", response.getStatusLine()
                        .getStatusCode());
                String responseContent = EntityUtils.toString(entity);
                JSONObject jsonObject = JSON.parseObject(responseContent);
                if (jsonObject.containsKey("Code") && jsonObject.getInteger("Code") == 0) {
                    return responseContent;
                } else {
                    return failResponse(jsonObject.getString("Message"), responseContent);
                }
            } else {
                log.error("httpRequest access fail ,StatusCode is:{}", response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            log.error("error :", e);
        } finally {
            post.releaseConnection();
        }
        return null;
    }

    @Override
    protected String sendSmsBatch(String[] mobiles, LinkedHashMap<String, String>[] params, String[] signatures, String[] templates) {
        return null;
    }
}
