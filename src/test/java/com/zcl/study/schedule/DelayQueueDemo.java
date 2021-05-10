package com.zcl.study.schedule;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * spring-demo .
 *
 * @description: 延迟队列DelayQueue.
 * @author: Chenglin Zhu .
 * @date: 20-4-17 .
 */

public class DelayQueueDemo {
    // 实现了Delayed接口的消息体
    static class DelayedMessage implements Delayed {
        private long activeTime;
        private String name;

        public DelayedMessage(String name, long activeTime) {
            this.name = name;
            this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime, TimeUnit.MILLISECONDS) + System.nanoTime(); //存活时间加当前时间
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(activeTime - System.nanoTime(), TimeUnit.NANOSECONDS); //剩余时间
        }

        @Override
        public int compareTo(Delayed o) {
            long t = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
            return t == 0 ? 0 : (t > 0 ? 1 : -1);
        }

        public String getName() {
            return name;
        }
    }


    static class Customer implements Runnable {
        // 消费消息的消费者
        private DelayQueue<DelayedMessage> queue;

        public Customer(DelayQueue<DelayedMessage> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                DelayedMessage message = queue.take();
                System.out.println(message.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayedMessage> queue = new DelayQueue<>();
        DelayedMessage delayedVo = new DelayedMessage("abc", 5000);
        queue.put(delayedVo);
        Customer customer = new Customer(queue);
        Thread thread = new Thread(customer);
        thread.start();
    }
}
