package com.zcl.study.spring.service;

import com.zcl.study.spring.model.Person;

/**
 * spring-study .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
public interface PersonService {
	String personName();

	int insertPerson(Person person);

	boolean deletePerson(String id);

	void testPerson(Person person);
}
