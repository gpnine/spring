package com.zcl.study.spring.dao;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class NewSingleton {
	private static NewSingleton instance;

	private NewSingleton() {
	}

	public static NewSingleton getInstance() {
		if (instance == null) {
			instance = new NewSingleton();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
}
