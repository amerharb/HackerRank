package com.hackerrank;
// https://www.hackerrank.com/challenges/get-the-value-of-the-node-at-a-specific-position-from-the-tail/problem

public class Main {

	public static void main(String[] args) {
		new Main();
	}

	private Main(){
		Node n = new Node(1);
		n.next = new Node(3);
		n.next.next = new Node(5);
		n.next.next.next = new Node(6);

		System.out.println(GetNode(n,0));
		System.out.println(GetNode(n,2));
	}

	class Node {
		int data;
		Node next;
		Node(int data){
			this.data = data;
		}
	}

	int GetNode(Node head, int n) {
		int count = 0;
		Node node = head;

		while(node != null){
			count++;
			node = node.next;
		}

		int p = count - n;
		node = head;
		for (int i = 1; i < p; i++) {
			node = node.next;
		}
		return node.data;
	}
}
