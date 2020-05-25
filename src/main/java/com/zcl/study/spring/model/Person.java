package com.zcl.study.spring.model;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * spring-study .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
public class Person implements InitializingBean {

	private String name;
	private int age;
	private String nickName;

	public void initPerson(){
		System.out.println("Person ..... init");
	}

	@PostConstruct
	public void init(){
		System.out.println("PostConstruct init");
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", nickName='" + nickName + '\'' +
				'}';
	}

	public Person() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Person(String name, int age, String nickName) {
		this.name = name;
		this.age = age;
		this.nickName = nickName;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet...方法");
	}
}
