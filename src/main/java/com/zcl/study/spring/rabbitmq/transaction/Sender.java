package com.zcl.study.spring.rabbitmq.transaction;

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

    private static final String QUEUE_NAME = "test_transaction";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg = "hello tx message";
        try {
            // AMQP自带的
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
//			int i = 1 / 0;
            System.out.println(msg);
            channel.txCommit();
        } catch (Exception e) {
            channel.txRollback();
            System.out.println("send message rollback");
        }

        channel.close();
        connection.close();
    }
}
