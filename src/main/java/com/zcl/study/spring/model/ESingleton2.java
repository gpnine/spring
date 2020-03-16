package com.zcl.study.spring.model;

import java.io.IOException;
import java.util.Properties;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class ESingleton2 {
	public static ESingleton2 INSTANCE;

	private String info;

	static {
		try {
			Properties properties = new Properties();
			properties.load(ESingleton2.class.getClassLoader().getResourceAsStream("single.properties"));
			INSTANCE = new ESingleton2(properties.getProperty("info"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ESingleton2(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "ESingleton2{" +
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
