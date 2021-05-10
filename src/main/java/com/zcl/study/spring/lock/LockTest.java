package com.zcl.study.spring.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-25 .
 */
public class LockTest {

    private static ThreadLocal<String> localVar = new ThreadLocal<>();

    private static void print(String str) {
        //打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }


    private Lock lock = new ReentrantLock();

    private void doBussiness() {
        String name = Thread.currentThread().getName();

        try {
            System.out.println(name + " 开始获取锁");
            lock.lockInterruptibly();
//			lock.lock();
            System.out.println(name + " 得到锁");
            System.out.println(name + " 开工干活");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(name + " : " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " 被中断");
            System.out.println(name + " 做些别的事情");
        } finally {
            try {
                lock.unlock();
                System.out.println(name + " 释放锁");
            } catch (Exception e) {
                System.out.println(name + " : 没有得到锁的线程运行结束");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        Thread t1 = new Thread(() -> {
            lockTest.doBussiness();
            //设置线程1中本地变量的值
            localVar.set("localVar1");
            //调用打印方法
            print("t1");
            //打印本地变量
            System.out.println("after remove : " + localVar.get());
        }, "t1");
        Thread t2 = new Thread(() -> {
            lockTest.doBussiness();
            //设置线程1中本地变量的值
            localVar.set("localVar2");
            //调用打印方法
            print("t2");
            //打印本地变量
            System.out.println("after remove : " + localVar.get());
        }, "t2");
        // 启动线程t1
        t1.start();
        Thread.sleep(10);
        // 启动线程t2
        t2.start();
        Thread.sleep(100);
        // 线程t1没有得到锁，中断t1的等待
        t2.interrupt();
    }
}
