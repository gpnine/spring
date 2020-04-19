package com.zcl.study.schedule;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * shzgh-lams-webapp .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-13 .
 */
public class TestScheduledExecutorService {

	public static void main(String[] args) {
		ScheduledExecutorService sc = Executors.newSingleThreadScheduledExecutor();
		ScheduledFuture future = sc.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(123456);
			}
		}, 1000, TimeUnit.MILLISECONDS);
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		ScheduledThreadPoolExecutor sc1 = new ScheduledThreadPoolExecutor(1);
		sc1.scheduleAtFixedRate(() -> System.out.println("asdf"), 0, 1, TimeUnit.SECONDS);
		ScheduledExecutorService sc2 = Executors.newScheduledThreadPool(2);
		sc2.scheduleWithFixedDelay(() -> System.out.println("bbbbbb"), 1, 1, TimeUnit.SECONDS);
	}

}
