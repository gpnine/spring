package com.zcl.study.spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-11 .
 */
@Configuration
@PropertySource("classpath:/db.properties")
public class MyProfile implements EmbeddedValueResolverAware {
	@Value("${db.username}")
	private String userName;
	private String driverClass;

	@Profile("dev")
	@Bean("devDataSource")
	public DataSource devDataSource(@Value("${db.password}") String pwd) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(userName);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl("jdbc://mysql:localhost:3306/test");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}

	@Profile("test")
	@Bean("testDataSource")
	public DataSource testDataSource(@Value("${db.password}") String pwd) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(userName);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl("jdbc://mysql:localhost:3306/test1");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}

	@Profile("prod")
	@Bean("prodDataSource")
	public DataSource prodDataSource(@Value("${db.password}") String pwd) throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(userName);
		dataSource.setPassword(pwd);
		dataSource.setJdbcUrl("jdbc://mysql:localhost:3306/test2");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
		driverClass = stringValueResolver.resolveStringValue("${db.driverClass}");
	}
}
