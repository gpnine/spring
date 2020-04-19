package com.zcl.study.schedule;

import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * shzgh-lams-webapp .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-13 .
 */
public class TestTimer {

	public static void main(String[] args) {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("asdfasd");
			}
		};
//		timer.schedule(task, 3000);
//		timer.cancel();
		timer.schedule(task, 1000, 1000);
//		TimerTask task2 = new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println("boom!!!");
//			}
//		};
//		try {
//			Date time = DateUtils.parseDate("2020-04-14 21:39:00", "yyyy-MM-dd HH:mm:ss");
//			timer.schedule(task2, time);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			Thread.sleep(4000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		TimerTask task3 = new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println(2/0);
//			}
//		};
//		timer.schedule(task3, 1000, 1000);
	}

	@Test
	public void testRightMove() {
		int period = 8;
		System.out.println(period >>= 2);
	}

}
