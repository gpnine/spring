package com.zcl.study;

import com.zcl.study.spring.model.LanSingleton;
import com.zcl.study.spring.model.LanSingleton1;
import com.zcl.study.spring.model.LanSingleton2;
import com.zcl.study.spring.model.ESingleton;
import com.zcl.study.spring.model.ESingleton1;
import com.zcl.study.spring.model.ESingleton2;
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
		ESingleton instance = ESingleton.INSTANCE;
		ESingleton instance2 = ESingleton.INSTANCE;
		System.out.println("方式一:" + instance);
		System.out.println("方式一:" + instance2);

		System.out.println("方式二:" + ESingleton1.INSANCE);
		System.out.println("方式三:" + ESingleton2.INSTANCE);
	}

	@Test
	public void testLanhan() {
		Callable<LanSingleton> callable = LanSingleton::getInstance;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		Future<LanSingleton> newSingletonFuture = executorService.submit(callable);
		Future<LanSingleton> newSingletonFuture2 = executorService.submit(callable);
		try {
			LanSingleton instance = newSingletonFuture.get();
			LanSingleton instance2 = newSingletonFuture2.get();
			System.out.println("方式一:" + instance);
			System.out.println("方式一:" + instance2);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		executorService.shutdown();
		System.out.println("方式二:" + LanSingleton1.getInstance());
		System.out.println("方式二:" + LanSingleton1.getInstance());
		System.out.println("方式三:" + LanSingleton2.getInstance());
		System.out.println("方式三:" + LanSingleton2.getInstance());
	}

}
