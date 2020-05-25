package com.zcl.study;

import com.zcl.study.spring.config.MyBeanConfig;
import com.zcl.study.spring.model.Y;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    @Test
    public void testSouce() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);
        Y x = (Y) context.getBean("x");
        System.out.println("x" + x);
    }
}
