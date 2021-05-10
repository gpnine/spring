package com.zcl.study.spring.controller;

import com.zcl.study.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-11 .
 */
@Controller
@RequestMapping("/person2")
public class PersonController2 {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/name")
    @ResponseBody
    public String getPersonName() {
        String personName = personService.personName();
        System.out.println(personName);
        return personName;
    }
}
