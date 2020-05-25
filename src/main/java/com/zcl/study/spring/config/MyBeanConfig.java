package com.zcl.study.spring.config;

import com.zcl.study.spring.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * spring-study .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
@Configuration
@ComponentScan({"com.zcl.study.spring.model",
        "com.zcl.study.spring.service",
        "com.zcl.study.spring.controller",
        "com.zcl.study.spring.dao",
        "com.zcl.study.spring.postprocessor"})
public class MyBeanConfig {

    @Bean(initMethod = "initPerson")
    public Person person(){
        Person person = new Person();
        person.setName("z3");
        person.setAge(1);
        person.setNickName("张三");
        return person;
    }
}
