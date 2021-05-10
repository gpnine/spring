package com.zcl.study;

/**
 * justice-xzxk-webapp .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-12 .
 */
public class BinarySort {
    private int data;
    private BinarySort left;
    private BinarySort right;

    public BinarySort(int data) {
        this.data = data;
    }

    public void insert(BinarySort root, int data) {
        if (root != null) {
            if (root.data < data) {
                if (root.right == null) {
                    root.right = new BinarySort(data);
                } else {
                    insert(root.right, data);
                }
            } else {
                if (root.left == null) {
                    root.left = new BinarySort(data);
                } else {
                    insert(root.left, data);
                }
            }
        }
    }

    public void in(BinarySort root) {
        if (root != null) {
            in(root.left);
            System.out.println(root.data);
            in(root.right);
        }

    }


    public static void main(String[] args) {
        int[] data = {1, 22, 3, 55, 66, 342, 56};
        BinarySort root = new BinarySort(data[0]);
        for (int i = 0; i < data.length; i++) {
            if (i != 0) {
                root.insert(root, data[i]);
            }

        }
        root.in(root);
    }

}
