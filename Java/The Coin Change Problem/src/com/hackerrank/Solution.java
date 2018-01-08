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
        long[] c = new long[m];
        for (int c_i = 0; c_i < m; c_i++) {
            c[c_i] = in.nextLong();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = getWays(n, c); //give the index of last elemint+1
        System.out.println(ways);
    }

    private long getWays(long n, long[] c) {

        Arrays.sort(c);
        long[] arr = new long[c.length];
        int i = arr.length;

        long ways = 0;

        while (i != 0) {
            long sum = n;
            for (int j = arr.length - 1; j > i - 1; j--) {
                sum -= arr[j] * c[j];
            }
            for (int j = i - 1; j >= 0; j--) {
                arr[j] = sum / c[j];
                sum -= arr[j] * c[j];
            }
            if (sum == 0) {
                ways++;
            }

            i = 0;
            for (int j = 1; j < arr.length; j++) {
                if (arr[j] > 0) {
                    arr[j]--;
                    i = j;
                    break;
                }
            }
        }
        return ways;
    }
}