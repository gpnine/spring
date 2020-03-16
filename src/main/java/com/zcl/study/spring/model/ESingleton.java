package com.zcl.study.spring.model;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class ESingleton {
	public static final ESingleton INSTANCE = new ESingleton();

	private ESingleton() {
	}
}
