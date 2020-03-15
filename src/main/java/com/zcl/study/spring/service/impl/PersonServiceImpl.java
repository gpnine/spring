package com.zcl.study.spring.service.impl;

import com.zcl.study.spring.dao.PersonDao;
import com.zcl.study.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
