package com.zcl.study.spring.structure;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-3-25 .
 */
public class MyLinkedList {
    private Node head = null;

    public void addNode(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void printNode() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
    }

    public int length() {
        int length = 0;
        Node currentNode = head;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

}
