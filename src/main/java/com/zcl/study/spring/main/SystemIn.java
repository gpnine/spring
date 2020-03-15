package com.zcl.study.spring.main;

import java.io.IOException;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-15 .
 */
public class SystemIn {
	public static void main(String[] args) {
		try {
			int read = System.in.read();
			System.out.println(read);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
