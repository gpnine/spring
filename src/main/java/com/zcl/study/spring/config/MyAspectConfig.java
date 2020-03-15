package com.zcl.study.spring.config;

import com.zcl.study.spring.calculation.MyCalculator;
import com.zcl.study.spring.event.MyAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
@EnableAspectJAutoProxy
@Configuration
@ComponentScan({"com.zcl.study.spring.calculation", "com.zcl.study.spring.event"})
public class MyAspectConfig {

	@Autowired
	public MyAspect myAspect;

	@Autowired
	public MyCalculator myCalculator;

}
