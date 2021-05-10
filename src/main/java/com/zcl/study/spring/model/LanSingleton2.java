package com.zcl.study.spring.model;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-14 .
 */
public class LanSingleton2 {
    private LanSingleton2() {
    }

    public static LanSingleton2 getInstance() {
        return InnerClass.instance;
    }

    private static class InnerClass {
        private static final LanSingleton2 instance = new LanSingleton2();
    }
}
