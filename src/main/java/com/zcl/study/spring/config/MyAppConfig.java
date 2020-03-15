package com.zcl.study.spring.config;

import com.zcl.study.spring.condition.MyCondition;
import com.zcl.study.spring.dao.MyDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * spring-study .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
@Configuration
@Conditional(MyCondition.class)
@ComponentScan({"com.zcl.study.spring.model", "com.zcl.study.spring.service", "com.zcl.study.spring.controller", "com.zcl.study.spring.dao"})
@Import(MyProfile.class)
public class MyAppConfig {

	@Scope("prototype")
	@Bean("myDao")
	public MyDao getMyDao() {
		return new MyDao();
	}
}
