package com.zcl.study;

import com.zcl.study.spring.model.Person;
import com.zcl.study.spring.proxy.PersonProxy;
import com.zcl.study.spring.service.PersonService;
import com.zcl.study.spring.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-21 .
 */
public class TestProxy {

	@Test
	public void test() {
		// JDK动态代理
		PersonService personService = new PersonServiceImpl();
		Person person = new Person();
		person.setName("kkk");
		PersonService proxyService = (PersonService) new PersonProxy().getProxyInstance(personService);
		proxyService.testPerson(person);
	}
}
