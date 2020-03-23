package com.zcl.study.spring.controller;

import com.zcl.study.spring.model.Person;
import com.zcl.study.spring.proxy.CglibProxy;
import com.zcl.study.spring.proxy.PersonProxy;
import com.zcl.study.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-11 .
 */
@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/name")
	public String getPersonName() {
		String personName = personService.personName();
		System.out.println(personName);
		return personName;
	}

	@PostMapping(value = "/add")
	public int addPerson(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		Person person = new Person();
		person.setName(name);
		PersonService proxyService = (PersonService) new PersonProxy().getProxyInstance(personService);
		return proxyService.insertPerson(person);
	}

	@GetMapping(value = "/delete")
	public boolean deletePerson(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		PersonService proxyService = (PersonService) new CglibProxy().getProxy(personService);
		return proxyService.deletePerson(id);
	}

	@GetMapping("/info")
	public String productInfo(){
		String currentUser;
		Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principl instanceof UserDetails) {
			currentUser = ((UserDetails)principl).getUsername();
		}else {
			currentUser = principl.toString();
		}
		return " some product info,currentUser is: "+currentUser;
	}
}
