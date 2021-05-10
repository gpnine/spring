package com.zcl.study.spring.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-24 .
 */
public class IsFairLock {

    private Lock lock = new ReentrantLock(false);

    private void testFail() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得了锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        IsFairLock testLock = new IsFairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            testLock.testFail();
        };
        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
    }
}
