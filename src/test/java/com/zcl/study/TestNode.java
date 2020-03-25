package com.zcl.study;

import com.zcl.study.spring.structure.MyLinkedList;
import org.junit.jupiter.api.Test;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-25 .
 */
public class TestNode {

	@Test
	public void testNode() {
		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.addNode(9);
		myLinkedList.addNode(8);
		myLinkedList.addNode(6);
		myLinkedList.addNode(3);
		myLinkedList.addNode(5);
		myLinkedList.printNode();
		System.out.println("节点个数：" + myLinkedList.length());
	}
}
