package com.zcl.study.schedule;

/**
 * shzgh-lams-webapp .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-13 .
 */
public class TestTask {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaaaa");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
