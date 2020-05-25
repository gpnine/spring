package com.zcl.study;

import com.zcl.study.spring.config.MyBeanConfig;
import com.zcl.study.spring.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SourceTest {

    @Test
    public void testSouce() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyBeanConfig.class);
        Person person = (Person) context.getBean("person");
        System.out.println("person" + person);
    }
}
