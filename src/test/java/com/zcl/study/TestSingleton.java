package com.zcl.study;

import com.zcl.study.spring.dao.NewSingleton;
import com.zcl.study.spring.dao.NewSingleton1;
import com.zcl.study.spring.dao.NewSingleton2;
import com.zcl.study.spring.dao.Singleton;
import com.zcl.study.spring.dao.Singleton1;
import com.zcl.study.spring.dao.Singleton2;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class TestSingleton {

	@Test
	public void testEhan() {
		Singleton instance = Singleton.INSTANCE;
		Singleton instance2 = Singleton.INSTANCE;
		System.out.println("方式一:" + instance);
		System.out.println("方式一:" + instance2);

		System.out.println("方式二:" + Singleton1.INSANCE);
		System.out.println("方式三:" + Singleton2.INSTANCE);
	}

	@Test
	public void testLanhan() {
		Callable<NewSingleton> callable = NewSingleton::getInstance;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<NewSingleton> newSingletonFuture = executorService.submit(callable);
		Future<NewSingleton> newSingletonFuture2 = executorService.submit(callable);
		try {
			NewSingleton instance = newSingletonFuture.get();
			NewSingleton instance2 = newSingletonFuture2.get();
			System.out.println("方式一:" + instance);
			System.out.println("方式一:" + instance2);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
		System.out.println("方式二:" + NewSingleton1.getInstance());
		System.out.println("方式二:" + NewSingleton1.getInstance());
		System.out.println("方式三:" + NewSingleton2.getInstance());
		System.out.println("方式三:" + NewSingleton2.getInstance());
	}

}
