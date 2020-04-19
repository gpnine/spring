package com.zcl.study.schedule;

import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

/**
 * shzgh-lams-webapp .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-13 .
 */
public class TestTaskSchedule implements Runnable {
	private ConcurrentTaskScheduler tpts = new ConcurrentTaskScheduler();
	private ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();

	private void start() {
//		tpts.schedule(this, new Date());
//		taskScheduler.setPoolSize(10);
//		//必须得先初始化，才能使用
		taskScheduler.initialize();
		taskScheduler.schedule(this, new CronTrigger("0/1 * * * * ?"));
	}

	public void run() {
		Thread ct = Thread.currentThread();
		System.out.println("current id:" + ct.getId());
		System.out.println("current name:" + ct.getName());
	}

	public static void main(String[] args) {
		new TestTaskSchedule().start();
	}
}
