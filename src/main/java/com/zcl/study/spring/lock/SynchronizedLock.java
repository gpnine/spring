package com.zcl.study.spring.lock;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-24 .
 */
public class SynchronizedLock {

    private static int count;
    private String lock1 = "";
    private String lock2 = new String();

    public static void main(String[] args) throws InterruptedException {
        // 对象头
        // 实例数据
        // 对齐补充字节
//		System.out.println(ClassLayout.parseInstance(l).toPrintable());
        SynchronizedLock testLock1 = new SynchronizedLock();
        SynchronizedLock testLock2 = new SynchronizedLock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                testLock1.add();
                add1();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                testLock2.add();
                add1();
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(count);
    }

    // 同步方法
    private synchronized void add() {
        count++;
    }

    private static synchronized void add1() {
        count++;
    }

    // 同步代码块
    private void add2() {
        // 给当前对象加锁
        synchronized (this) {
            count++;
        }
    }

    private void add3() {
        // 给其他对象加锁
        synchronized (lock1) {
            count++;
        }
    }

    private void add4() {
        // 给其他对象加锁
        synchronized (lock2) {
            count++;
        }
    }

    private void add5() {
        // 给类对象加锁
        synchronized (SynchronizedLock.class) {
            count++;
        }
    }
}
