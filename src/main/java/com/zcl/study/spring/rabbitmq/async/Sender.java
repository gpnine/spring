package com.zcl.study.spring.rabbitmq.async;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.zcl.study.spring.util.ConnectUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-15 .
 */
public class Sender {

    private static final String QUEUE_NAME = "test_confirm_set";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.confirmSelect();

        SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());
        // 添加监听同道
        channel.addConfirmListener(new ConfirmListener() {
            // 没有问题的handleAck
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("handleAck multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("handleAck multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("handleNack multiple");
                    confirmSet.headSet(deliveryTag + 1).clear();
                } else {
                    System.out.println("handleNack multiple false");
                    confirmSet.remove(deliveryTag);
                }
            }
        });

        String msgSet = "sssss";

        while (true) {
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("", QUEUE_NAME, null, msgSet.getBytes());
            confirmSet.add(seqNo);
        }
    }
}
