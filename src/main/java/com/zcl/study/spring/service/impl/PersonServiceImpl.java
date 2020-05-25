package com.zcl.study.spring.service.impl;

import com.zcl.study.spring.dao.PersonDao;
import com.zcl.study.spring.model.Person;
import com.zcl.study.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-11 .
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public String personName() {
        return personDao.getPersonName();
    }

    @Override
    public void testPerson(Person person) {
        System.out.println("保存操作");
    }

    @Override
    public void sayHello() {
        System.out.println("hello");
    }

    @Override
    public int insertPerson(Person person) {
        return personDao.addPerson(person);
    }


    @Override
    public boolean deletePerson(String id) {
        return personDao.deletePerson(id);
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void getName() {
        System.out.println(personDao.getPersonName());
    }
}
