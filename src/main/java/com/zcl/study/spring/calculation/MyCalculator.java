package com.zcl.study.spring.calculation;

import org.springframework.stereotype.Component;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
@Component
public class MyCalculator {

	public int div(int m,int n){
		int i = m / n;
		return i;
	}
}
