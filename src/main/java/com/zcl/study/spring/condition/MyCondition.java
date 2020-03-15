package com.zcl.study.spring.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
public class MyCondition implements Condition {
	@Override
	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
		ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
		Environment environment = conditionContext.getEnvironment();
		BeanDefinitionRegistry registry = conditionContext.getRegistry();
		String property = environment.getProperty("os.name");
		if (property.equals("Linux")) {
			return true;
		}
		return false;
	}
}
