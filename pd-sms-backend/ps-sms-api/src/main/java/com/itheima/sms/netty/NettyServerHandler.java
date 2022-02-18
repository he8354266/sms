package com.itheima.sms.netty;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/1015:43
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.pinda.utils.SpringUtils;
import com.itheima.sms.dto.SmsParamsDTO;
import com.itheima.sms.service.impl.SmsSendServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/10 15:43
 * @updateDate 2022/2/10 15:43
 **/
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // TODO 短信接收服务：接收应用系统的报文并解析，调用Service将消息保存到消息缓冲区
        log.info("开始解析报文:{}", msg);
        String resp = "success";
        try {
            //开始将String类型的报文转换为SmsParamsDTO类型的对象
            SmsParamsDTO smsParamsDTO = parseMessage(msg);
            if (smsParamsDTO == null) {
                //报文解析失败，不能发送短信
                log.error("报文解析失败");
                return;
            }
            SpringUtils.getBean(SmsSendServiceImpl.class).send(smsParamsDTO);
        } catch (Exception ex) {
            resp = ex.getMessage();
        }
        //向客户端写会数据
        ctx.writeAndFlush(resp + "\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        log.info("收到客户端[ip:" + clientIp + "]连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        ctx.close();
    }

    /**
     * 解析报文
     * <p>
     * 设备不同报文也不同，直接使用json格式传输
     */
    private SmsParamsDTO parseMessage(String body) {
        if (StringUtils.isBlank(body)) {
            log.warn("报文为空");
            return null;
        }
        body = body.trim();
        // 其它格式的报文需要解析后放入SmsParamsDTO实体
        SmsParamsDTO message = JSON.parseObject(body, SmsParamsDTO.class);
        if (message == null || StringUtils.isBlank(message.getMobile()) || StringUtils.isBlank(message.getSignature()) || StringUtils.isBlank(message.getTemplate())) {
            log.warn("报文内容异常");
            return null;
        }
        return message;
    }
}
