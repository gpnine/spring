package com.zcl.study.spring.dao;

import java.io.IOException;
import java.util.Properties;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class Singleton2 {
	public static Singleton2 INSTANCE;

	private String info;

	static {
		try {
			Properties properties = new Properties();
			properties.load(Singleton2.class.getClassLoader().getResourceAsStream("single.properties"));
			INSTANCE = new Singleton2(properties.getProperty("info"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Singleton2(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Singleton2{" +
				"info='" + info + '\'' +
				'}';
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
