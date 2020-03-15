package com.zcl.study.spring.rabbitmq.topic;

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

	private static final String EXCHANGE_NAME = "test_topic_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectUtils.getConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");

		String routeKey = "Goods.2";
		channel.basicPublish(EXCHANGE_NAME, routeKey, false, null, "topic方式".getBytes());
		channel.close();
		connection.close();
	}

}
