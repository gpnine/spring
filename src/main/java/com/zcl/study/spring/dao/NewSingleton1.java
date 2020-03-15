package com.zcl.study.spring.dao;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class NewSingleton1 {
	private static NewSingleton1 instance;

	private NewSingleton1() {
	}

	public static NewSingleton1 getInstance() {
		if (instance == null) {
			synchronized (NewSingleton1.class) {
				if (instance == null) {
					instance = new NewSingleton1();
				}
			}
		}
		return instance;
	}
}
