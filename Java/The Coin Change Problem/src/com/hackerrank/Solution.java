package com.hackerrank;
/**
 * Created by Amer on 08-Jan-18.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        new Solution();
    }

    public Solution() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] c = new int[m];
        for (int c_i = 0; c_i < m; c_i++) {
            c[c_i] = in.nextInt();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long w = System.currentTimeMillis();
        long ways = getWays(n, c); //give the index of last elemint+1
        System.out.println(ways);
        System.out.println((System.currentTimeMillis() - w) + " mili sec");
    }

    private long getWays(int n, int[] c) {

        long[] t = new long[n + 1];
        t[0] = 1;

        for (int i = 0; i < c.length; i++) {
            for (int j = c[i]; j < n + 1; j++) {
                t[j] += t[j - c[i]];
            }
        }
        return t[n];
    }

}