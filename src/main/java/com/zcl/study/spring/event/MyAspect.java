package com.zcl.study.spring.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
@Component
@Aspect
public class MyAspect {

	@Pointcut(value = "execution(* com.zcl.study.spring.calculation.MyCalculator.*(..))")
	public void pointCut() {

	}

	@Before(value = "execution(public int com.zcl.study.spring.calculation.MyCalculator.div(..))")
	public void beforeEvent(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.println(name + "方法执行前");
	}

	@After(value = "pointCut()")
	public void afterEvent(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		System.out.println(name + "方法执行后");
	}

	@AfterThrowing(value = "pointCut()", throwing = "exception")
	public void throwEvent(JoinPoint joinPoint, Throwable exception) {
		String name = joinPoint.getSignature().getName();
		System.out.println(name + "异常" + exception.getLocalizedMessage());
	}

	@AfterReturning(value = "pointCut()", returning = "result")
	public void returnEvent(JoinPoint joinPoint, Integer result) {
		String name = joinPoint.getSignature().getName();
		System.out.println(name + "方法返回" + result);
	}
}
