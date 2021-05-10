package com.zcl.study.spring.model;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class LanSingleton1 {
    private static LanSingleton1 instance;

    private LanSingleton1() {
    }

    public static LanSingleton1 getInstance() {
        if (instance == null) {
            synchronized (LanSingleton1.class) {
                if (instance == null) {
                    instance = new LanSingleton1();
                }
            }
        }
        return instance;
    }
}
