package com.zcl.study.spring.rabbitmq.workfair;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.zcl.study.spring.util.ConnectUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class Reciver {

    // 耦合性高，对多个消费者就不行了
    private static final String QUEUE_NAME = "mq_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectUtils.getConnection();
        Channel channel = connection.createChannel();
        //　声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 服务端限流
        // 消费完prefetchCount条（prefetchCount条消息被ack）才再次推送
        channel.basicQos(1);
        //　定义消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body, StandardCharsets.UTF_8));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("[1] done");
                    // 手动回执
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };
        //　监听队列
        boolean autoAck = false; // 自动应答,false等待消费者显示回复确认,true不管消费者是否真正的消费了这些消息直接设置为确认
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

    }

}
