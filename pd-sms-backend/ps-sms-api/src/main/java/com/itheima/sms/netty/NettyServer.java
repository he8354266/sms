package com.itheima.sms.netty;/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2022/2/1014:36
 */

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



    @Override
    public void run(String... args) throws Exception {

    }
}
