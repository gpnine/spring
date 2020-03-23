package com.zcl.study.spring.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * spring-demo .
 *
 * @description: cglib动态代理.
 * @author: Chenglin Zhu .
 * @date: 20-3-21 .
 */
public class CglibProxy implements MethodInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(CglibProxy.class);

	private Object target;

	public Object getProxy(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer(); // 增強器
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		LOGGER.info("调用方法之前日志输出");
		Object invoke = method.invoke(target, objects);
		LOGGER.info("调用方法之后日志输出");
		return invoke;
	}
}
