package com.zcl.study.spring.rabbitmq.publishAndSubscribe;

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
public class Publisher {

	private static final String EXCHANGE_TYPE = "fanout";
	private static final String EXCHANGE_NAME = "test_queue_fanout";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
		// 往交换机里发
		channel.basicPublish(EXCHANGE_NAME, "", null, "分发".getBytes());
		channel.close();
		connection.close();
	}
}
