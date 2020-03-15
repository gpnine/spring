package com.zcl.study.spring.dao;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class NewSingleton2 {
	private NewSingleton2() {
	}

	public static NewSingleton2 getInstance() {
		return InnerClass.instance;
	}

	private static class InnerClass {
		private static final NewSingleton2 instance = new NewSingleton2();
	}
}
