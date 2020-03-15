package com.zcl.study.spring.rabbitmq.workfair;

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
 * @date: 20-3-14 .
 */
public class Sender {

	private static final String QUEUE_NAME = "mq_test";

	// 轮询分发，每个人一次接收一个，不管谁忙谁闲
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = ConnectUtils.getConnection();
		Channel channel = connection.createChannel();
		// 每间隔时间请求数
		channel.basicQos(1);
		for (int i = 0; i < 50; i++) {
			String msg = "hello work queue" + i;
			System.out.println("Publisher msg:" + msg);
			//　发布
			channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
			Thread.sleep(i * 5);
		}
		channel.close();
		connection.close();
	}
}
