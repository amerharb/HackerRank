package com.hackerrank;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        new Main();
    }

    Main() {
        Node n1 = new Node();
        n1.data = 1;
        n1.next = new Node();

        n1.next.data = 2;
        n1.next.next = new Node();

        n1.next.next.data = 3;
        n1.next.next.next = null;

        Node n2 = new Node();
        n2.data = 1;
        n2.next = n1.next;

        System.out.println(FindMergeNode(n1, n2));

    }

    class Node {
        int data;
        Node next;
    }

    int FindMergeNode(Node headA, Node headB) {

        int[] list = new int[100]; //save list A here
        Node n = headA;

        for (int i = 0; n != null; i++) {
            list[i] = n.hashCode();
            n = n.next;
        }

        Arrays.sort(list);
        n = headB;
        while(n != null) {
            if (Arrays.binarySearch(list, n.hashCode()) > 0) {
                return n.data;
            }
            n = n.next;
        }
        return 0;
    }
}


