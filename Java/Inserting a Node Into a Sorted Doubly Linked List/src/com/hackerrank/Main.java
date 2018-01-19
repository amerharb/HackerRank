package com.hackerrank;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        //2 5 4
        Node n = null;
        int[] arr = {2,5,4};
        for (int i = 0; i < arr.length; i++) {
            n = SortedInsert(n, arr[i]);
        }

        while(n != null){
            System.out.print(n.data + ", ");
            n = n.next;
        }

    }

    class Node {
        int data;
        Node next;
        Node prev;
    }

    Node SortedInsert(Node head, int data) {

        Node n = new Node();
        n.data = data;
        if (head == null) {
            return n;
        } else if (n.data <= head.data) {
            n.next = head;
            head.prev = n;
            return n;
        } else {
            Node cN = head;
            Node lastN = cN;
            while (cN != null && n.data > cN.data) {
                lastN = cN;
                cN = cN.next;
            }
            if (cN == null) {
                n.prev = lastN;
                lastN.next = n;
            } else {
                cN.prev.next = n;
                n.next = cN;
                n.prev = cN.prev;
                cN.prev = n;
            }
            return head;
        }
    }
}
