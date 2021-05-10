package com.zcl.study;

import com.zcl.study.spring.structure.MyLinkedList;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * spring-demo .
 *
 * @description: 稀疏数组.
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
        HashMap<Object, Object> map = new HashMap<>();
    }

    @Test
    public void testXishuList() {
        int[][] arr = new int[11][11];
        arr[1][2] = 3;
        arr[2][4] = 4;
        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
                if (anInt != 0) {
                    sum++;
                }
            }
            System.out.println();
        }

        // 转成稀疏数组
        int[][] array = new int[sum + 1][3];
        array[0][0] = 11;
        array[0][1] = 11;
        array[0][2] = sum;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                int anInt = arr[i][j];
                if (anInt != 0) {
                    count++;
                    array[count][0] = i;
                    array[count][1] = j;
                    array[count][2] = anInt;
                }
            }
        }
        System.out.println();
        System.out.println("稀疏数组形式为-----");
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

        int[][] arrs = new int[array[0][0]][array[0][1]];
        for (int i = 1; i < array.length; i++) {
            arrs[array[i][0]][array[i][1]] = array[i][2];
        }
        System.out.println();
        System.out.println("二维数组形式为-----");
        for (int[] ints : arrs) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
    }
}
