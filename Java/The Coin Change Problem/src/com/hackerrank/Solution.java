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

        long w = System.currentTimeMillis();
        long ways = getWays2(n, c); //give the index of last elemint+1
        //long ways = getWays(n, c); //give the index of last elemint+1
        System.out.println(ways);
        System.out.println((System.currentTimeMillis() - w) + " mili sec");
    }

    private long getWays2(int n, long[] c) {

        long[] t = new long[n + 1];
        t[0] = 1;

        for (int i = 0; i < c.length; i++) {
            for (long j = c[i]; j < n + 1; j++) {
                int ii = (int)j;
                t[ii] += t[ii - (int)c[i]];
            }
        }
        return t[n];
    }

    private long getWays(long n, long[] c) {

        Arrays.sort(c);
        System.out.println(Arrays.toString(c));
        long[] a = new long[c.length];
        int i = a.length;

        long ways = 0;

        /*if (c.length >= 4 && c[3] % c[2] == 0 && c[2] % c[1] == 0 && c[1] % c[0] == 0) {
            while (i != 0) {
                long sum = n;
                for (int j = a.length - 1; j > i - 1; j--) {
                    sum -= a[j] * c[j];
                }
                for (int j = i - 1; j >= 0; j--) {
                    a[j] = sum / c[j];
                    sum -= a[j] * c[j];
                }
                if (sum == 0) {
                    ways += ((a[2] + 1) * (a[3] + 1)) + (getSerSum(a[3]) * c[2]);
                }

                i = 0;
                for (int j = 4; j < a.length; j++) {
                    if (a[j] > 0) {
                        a[j]--;
                        i = j;
                        break;
                    }
                }
            }
        } else */
        if (c.length >= 3 && c[2] % c[1] == 0 && c[1] % c[0] == 0) {
            while (i != 0) {
                long sum = n;
                for (int j = a.length - 1; j > i - 1; j--) {
                    sum -= a[j] * c[j];
                }
                for (int j = i - 1; j >= 0; j--) {
                    a[j] = sum / c[j];
                    sum -= a[j] * c[j];
                }
                if (sum == 0) {
                    ways += ((a[1] + 1) * (a[2] + 1)) + (getSerSum(a[2]) * c[1]);
                }

                i = 0;
                for (int j = 3; j < a.length; j++) {
                    if (a[j] > 0) {
                        a[j]--;
                        i = j;
                        break;
                    }
                }
            }
        } else if (c.length >= 2 && c[1] % c[0] == 0) {
            while (i != 0) {
                long sum = n;
                for (int j = a.length - 1; j > i - 1; j--) {
                    sum -= a[j] * c[j];
                }
                for (int j = i - 1; j >= 0; j--) {
                    a[j] = sum / c[j];
                    sum -= a[j] * c[j];
                }
                if (sum == 0) {
                    ways += a[1] + 1;
                }

                i = 0;
                for (int j = 2; j < a.length; j++) {
                    if (a[j] > 0) {
                        a[j]--;
                        i = j;
                        break;
                    }
                }
            }
        } else {
            while (i != 0) {
                long sum = n;
                for (int j = a.length - 1; j > i - 1; j--) {
                    sum -= a[j] * c[j];
                }
                for (int j = i - 1; j >= 0; j--) {
                    a[j] = sum / c[j];
                    sum -= a[j] * c[j];
                }
                if (sum == 0) {
                    ways++;
                }

                i = 0;
                for (int j = 1; j < a.length; j++) {
                    if (a[j] > 0) {
                        a[j]--;
                        i = j;
                        break;
                    }
                }
            }
        }
        return ways;
    }

    private long getSerSum(long x) {
        long t = 0;
        for (long l = x; l > 0; l--) {
            t += l;
        }
        return t;
    }
}