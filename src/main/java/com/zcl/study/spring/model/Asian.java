package com.zcl.study.spring.model;

import org.springframework.stereotype.Component;

/**
 * spring-study .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-10 .
 */
@Component
@com.zcl.study.spring.annotation.Asian
public class Asian extends Person{

	private String name;
	private int age;
	private String nickName;

	@Override
	public String toString() {
		return "Asian{" +
				"name='" + name + '\'' +
				", age=" + age +
				", nickName='" + nickName + '\'' +
				'}';
	}

	public Asian() {
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

	public Asian(String name, int age, String nickName) {
		this.name = name;
		this.age = age;
		this.nickName = nickName;
	}
}
