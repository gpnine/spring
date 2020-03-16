package com.zcl.study.spring.model;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class LanSingleton {
	private static LanSingleton instance;

	private LanSingleton() {
	}

	public static LanSingleton getInstance() {
		if (instance == null) {
			instance = new LanSingleton();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
}
