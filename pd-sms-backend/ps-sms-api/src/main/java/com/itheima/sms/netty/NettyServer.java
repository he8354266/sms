package com.itheima.sms.netty;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/1014:36
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zkjy
 * @version 1.0
 * @description zkjy
 * @updateUser
 * @createDate 2022/2/10 14:36
 * @updateDate 2022/2/10 14:36
 **/
@Component
@Slf4j
public class NettyServer implements CommandLineRunner {

    private static NettyServer nettyServer;

    @PostConstruct
    public void init() {
        nettyServer = this;
    }

    @Value("${netty.port:10001}")
    private int port;

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;


    public void start() {
        mainGroup = new NioEventLoopGroup(2);
        subGroup = new NioEventLoopGroup(4);
        try {
            server = new ServerBootstrap();
            server.group(mainGroup, subGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new NettyServerInitializer());

            //绑定端口，开始接收进来的连接
            future = server.bind(port).sync();
            log.error("netty服务启动: [port:" + port + "]");
            //等待服务器socket关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("netty服务启动异常-" + e.getMessage());
        } finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }


    @Override
    public void run(String... args) throws Exception {
        new Thread("netty") {
            @Override
            public void run() {
                nettyServer.start();
            }
        }.start();
    }
}
