package com.zcl.study.spring.dao;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class Singleton {
	public static final Singleton INSTANCE = new Singleton();

	private Singleton() {
	}
}
