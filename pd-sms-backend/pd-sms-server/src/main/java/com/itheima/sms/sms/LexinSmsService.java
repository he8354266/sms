package com.itheima.sms.sms;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/3/316:58
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.sms.entity.SignatureEntity;
import com.itheima.sms.entity.SmsConfig;
import com.itheima.sms.entity.TemplateEntity;
import com.itheima.sms.utils.StringHelper;
import io.undertow.util.StatusCodes;
import lombok.extern.slf4j.Slf4j;
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
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/3/3 16:58
 * @updateDate 2022/3/3 16:58
 **/
@Slf4j
public class LexinSmsService extends AbstractSmsService {
    public LexinSmsService(SmsConfig config) {
        this.config = config;
    }

    @Override
    protected String sendSms(String mobile, Map<String, String> params, String signature, String template) {
        // 获取 签名内容 和模板id
        SignatureEntity signatureEntity = signatureService.getByCode(signature);
        TemplateEntity templateEntity = templateService.getByCode(template);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(config.getDomain() + config.get("single_send"));
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            List postParams = new ArrayList();
            postParams.add(new BasicNameValuePair("accName", config.getAccessKeyId()));
            postParams.add(new BasicNameValuePair("accPwd", config.getAccessKeySecret()));
            postParams.add(new BasicNameValuePair("aimcodes", mobile));
            postParams.add(new BasicNameValuePair("content", "【" + signatureEntity.getContent() + "】" + StringHelper.renderString(templateEntity.getContent(), params)));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParams, "UTF-8");
            post.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(post);
            if (response.getStatusLine().getStatusCode() == StatusCodes.OK) {
                log.info("httpRequest access success, StatusCode is:{}", response.getStatusLine()
                        .getStatusCode());
                String responseContent = EntityUtils.toString(entity);
                JSONObject jsonObject = JSON.parseObject(responseContent);
                if (jsonObject.containsKey("replyCode") && jsonObject.getInteger("replyCode") == 1) {
                    return responseContent;
                } else {
                    return failResponse(jsonObject.getString("replyMsg"), responseContent);
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
