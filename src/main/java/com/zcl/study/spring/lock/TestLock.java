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
public class TestLock {

	private Lock lock = new ReentrantLock();
	private static int count;

	private void add() {
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TestLock testLock = new TestLock();
		new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				testLock.add();
			}
		}).start();
		new Thread(() -> {
			for (int i = 0; i < 10000; i++) {
				testLock.add();
			}
		}).start();
		Thread.sleep(10000);
		System.out.println(count);
	}

}
