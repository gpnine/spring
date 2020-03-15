package com.zcl.study;

import com.zcl.study.spring.config.MyAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@SpringBootTest
class SpringStudyApplicationTests {

	@Test
	void contextLoads() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyAppConfig.class);
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames).forEach(System.out::println);
	}
}
