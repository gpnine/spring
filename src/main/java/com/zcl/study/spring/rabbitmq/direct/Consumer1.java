package com.zcl.study.spring.rabbitmq.direct;

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
 * @date: 20-3-15 .
 */
public class Consumer1 {
	private static final String EXCHANGE_NAME = "test_direct_exchange";
	private static final String QUEUE_NAME = "test_queue_direct_1";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectUtils.getConnection();
		Channel channel = connection.createChannel();
		// 消息没有持久化，消费者如果挂掉，重启后会阻塞，消息会堆积，不会去消费
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
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
