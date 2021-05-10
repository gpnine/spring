package com.zcl.study.spring.util;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * spring-demo .
 *
 * @description: mq连接.
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class ConnectUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHost("192.168.206.8");
        factory.setVirtualHost("/");
        factory.setPort(5672);
        return factory.newConnection();
    }
}
