package com.zcl.study.spring.lock;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-26 .
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁.
 *
 * @author Torey.
 */
public class CacheDemo {
	// 创建一个缓存对象
	static Map<String, Object> cache = Collections
			.synchronizedMap(new HashMap<>());
	static ReadWriteLock rwLock = new ReentrantReadWriteLock();

	/**
	 * 定义一个获取数据的方法.
	 *
	 * @param key .
	 * @return .
	 */
	public Object getData(String key) {
		// 开启读锁
		rwLock.readLock().lock();
		Object value = null;
		try {
			// 获取到缓冲对象中的数据
			value = cache.get(key);
			if (value == null) {
				// 关闭读锁
				rwLock.readLock().unlock();
				// 开启 写锁
				rwLock.writeLock().lock();
				try {
					value = cache.get(key);
					if (value == null) {
						System.out.println("从DB获取数据：" + key);
						cache.put(key, key + "_key的值");
						value = cache.get(key);
					} else {
						System.out.println("从缓存读取：");
						value = cache.get(key);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					// 释放写锁
					rwLock.writeLock().unlock();
				}
				// 释放读锁
				rwLock.readLock().lock();
			} else {
				value = cache.get(key);
				System.out.println("从缓存读取" + value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 关闭读锁
			rwLock.readLock().unlock();
		}
		return value;
	}

	static class CacheRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (int i = 0; i < 3; i++) {
				System.out.println(Thread.currentThread().getName() + "_" + i + "=" + new CacheDemo().getData(Thread.currentThread().getName() + "_" + i));
			}
		}

	}

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new CacheRunnable(), "线程").start();
		}
	}
}
