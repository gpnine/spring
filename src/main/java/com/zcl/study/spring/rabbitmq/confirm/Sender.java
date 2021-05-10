package com.zcl.study.spring.rabbitmq.confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zcl.study.spring.util.ConnectUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-15 .
 */
public class Sender {

    private static final String QUEUE_NAME = "test_confirm";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.confirmSelect();
        // 批量发送
        for (int i = 0; i < 10; i++) {
            String msg = "hello tx message" + i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println(msg);
        }
        // 发完之后再确认
        if (channel.waitForConfirms()) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        channel.close();
        connection.close();
    }
}
