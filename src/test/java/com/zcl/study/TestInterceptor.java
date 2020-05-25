package com.zcl.study;

import com.zcl.study.spring.proxy.InterceptorJdkProxy;
import com.zcl.study.spring.service.PersonService;
import com.zcl.study.spring.service.impl.PersonServiceImpl;

public class TestInterceptor {

    public static void main(String[] args) {
        PersonService personService = (PersonService) InterceptorJdkProxy.bind(new PersonServiceImpl(), "com.zcl.study.spring.inteceptor.MyInterceptor");
        personService.sayHello();
    }
}
