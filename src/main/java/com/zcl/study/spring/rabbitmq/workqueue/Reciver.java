package com.zcl.study.spring.rabbitmq.workqueue;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
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
		reciver(channel);

	}

	private static void reciver(Channel channel) throws IOException {
		//　定义消费者
		DefaultConsumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				System.out.println(new String(body, StandardCharsets.UTF_8));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		//　监听队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
