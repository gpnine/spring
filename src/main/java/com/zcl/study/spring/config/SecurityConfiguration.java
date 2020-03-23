package com.zcl.study.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-23 .
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("admin") // 管理员，同事具有 ADMIN,USER权限，可以访问所有资源
				.password("{noop}admin")  //
				.roles("ADMIN", "USER")
				.and()
				.withUser("user1").password("{noop}user1") // 普通用户，只能访问 /product/**
				.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/person/**").hasRole("USER")
				.antMatchers("/info/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin().and()
				.httpBasic();
	}

}
