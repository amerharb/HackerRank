package com.hackerrank;

import java.util.*;

public class Solution {

	static String super_reduced_string(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.substring(i, i + 1).equals(s.substring(i + 1, i + 2))) {
				return super_reduced_string(s.substring(0, i) + s.substring(i + 2, s.length()));
			}
		}

		if (s.isEmpty()) {
			return "Empty String";
		} else {
			return s;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		String result = super_reduced_string(s);
		System.out.println(result);
	}
}
