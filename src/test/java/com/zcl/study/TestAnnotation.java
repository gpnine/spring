package com.zcl.study;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zcl.study.spring.calculation.MyCalculator;
import com.zcl.study.spring.config.MainConfigOfProfile;
import com.zcl.study.spring.config.MyAppConfig;
import com.zcl.study.spring.config.MyAspectConfig;
import com.zcl.study.spring.config.MyProfile;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
public class TestAnnotation {


	@Test
	public void testAspect() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAspectConfig.class);
		MyCalculator myCalculator = applicationContext.getBean(MyCalculator.class);
		myCalculator.div(2,1);
	}

	@Test
	public void testQualifier() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAppConfig.class);
		String[] beanNamesForType = applicationContext.getBeanDefinitionNames();
		System.out.println(Arrays.asList(beanNamesForType));
		applicationContext.close();
	}

	@Test
	public void testDataSource() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyProfile.class);
		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		System.out.println(Arrays.asList(beanNamesForType));
		applicationContext.close();

	}

	@Test
	public void testProfileBean() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyProfile.class);
		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		System.out.println(Arrays.asList(beanNamesForType));
		applicationContext.close();

	}

	@Test
	public void getBeanNames() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyProfile.class);
		applicationContext.getEnvironment().setActiveProfiles("dev", "prod");
		applicationContext.register(MyProfile.class);
		applicationContext.refresh();
		System.out.println("==================");
		String[] beanNamesForType1 = applicationContext.getBeanNamesForType(DataSource.class);
		System.out.println(Arrays.asList(beanNamesForType1));
		applicationContext.close();

	}

	@Test
	public void testProfile1() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		System.out.println(Arrays.toString(beanNamesForType));
	}

	@Test
	public void testProfile() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
		//1. 创建一个applicationContext
		//2. 设置需要激活的环境
		applicationContext.getEnvironment().setActiveProfiles("dev");
		//3. 注册主配置类
		applicationContext.register(MainConfigOfProfile.class);
		//4. 启动刷新容器
		applicationContext.refresh();

		String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
		System.out.println(Arrays.toString(beanNamesForType));

		applicationContext.close();
	}

	@Test
	public void testString() {
		boolean b = StringUtils.pathEquals("1", "3");
		System.out.println(b);
	}


	private final static ExecutorService executor = Executors.newCachedThreadPool();//启用多线程
	private volatile int count;
	private int inci_2 = 0;// 这个是线程2的变量

	@Test
	void testCount() throws InterruptedException {
		Inc2 inc2 = new Inc2();
		Thread t2 = new Thread(inc2);
		t2.start();
		t2.join();
		System.out.println("＝＝＝＝" + inci_2);
	}

	class Inc2 implements Runnable {
		public void run() {
			for (int i = 0; i < 300000000; i++) {
				inci_2++;
			}
		}
	}

	@Test
	void testCount1() throws InterruptedException {
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 300000000; i++) {
					inci_2++;
					count++;
				}
			}
		});
		t2.start();
		t2.join();
		System.out.println("＝＝＝＝" + inci_2);
		System.out.println("＝＝＝＝" + count);
	}

	@Test
	void testC3p0Connection() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		try {
			dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
			String sql = "SELECT name FROM user";
			Statement stmt;
			String name = "";
			Connection connection = dataSource.getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString("name");
				System.out.println(name);
			}
		} catch (PropertyVetoException | SQLException e) {
			e.printStackTrace();
		}

	}

}
