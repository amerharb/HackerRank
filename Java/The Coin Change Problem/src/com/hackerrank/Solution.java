package com.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Amer on 02-Jan-18.
 */
public class Solution {
    private int col;//current column working

    private static int curCol = 0;
    private static TaskStopWatch watch;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] c = new long[m];
        for (int c_i = 0; c_i < m; c_i++) {
            c[c_i] = in.nextLong();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        long w = System.currentTimeMillis();
        watch = new TaskStopWatch("main");
        long ways = getWays(n, c);
        System.out.println(ways);
        //TEST:
        System.out.println("sec: " + ((System.currentTimeMillis() - w) / 1000));
        System.out.println(watch.getReport());
    }

    static long timeFor1MWays;

    static long getWays(long n, long[] c) {
        long ways = 0;

        Arrays.sort(c);
        int[] idx = new int[c.length];
        int[] idxMax = getIdxMax(idx, c, n);

        while (curCol < c.length) {
            watch.setTask("getingn Total");
            long total = getTotal(idx, c);
            if (total < n) {
                watch.setTask("Total < n");
                //incIdx(idx, c, idxMax);
                //incIdx(idx, c, idxMax, (n - total));
                incIdxBy(idx, c, idxMax, (n - total));
            } else if (total == n) { //found total
                watch.setTask("Total == n");
                ways++;
                //TEST:
                if (ways % 1000000 == 0) {
                    System.out.println("ways: " + ways + " mili sec: " + ((System.currentTimeMillis() - timeFor1MWays)));
                    timeFor1MWays = System.currentTimeMillis();
                }
                skipFirstCol(idx, c, idxMax);

            } else { // total > n
                watch.setTask("Total > n");
                int colToSkip = -1;
                for (int i = 0; i <= curCol; i++) {
                    int[] tempIdx = new int[idx.length];
                    for (int j = 0; j < i; j++) {
                        tempIdx[j] = 0;
                    }
                    if (idx[i] < idxMax[i]) {
                        tempIdx[i] = idx[i] + 1;
                    } else {
                        continue;
                    }
                    for (int j = i + 1; j < idx.length; j++) {
                        tempIdx[j] = idx[j];
                    }

                    if (getTotal(tempIdx, c) > n) {
                        //skipForCol(idx, c, idxMax, i);
                        colToSkip++;
                    } else {
                        break;
                    }
                }
                skipForCol(idx, c, idxMax, colToSkip);
            }
        }
        return ways;
    }

    private static void skipForCol(int[] idx, long[] c, int[] idxMax, int col) {
        for (int i = col; i >= 0; i--) {
            idx[i] = idxMax[i];
        }
        incIdx(idx, c, idxMax);
    }

    private static void skipCurCol(int[] idx, long[] c, int[] idxMax) {
        for (int i = curCol - 1; i >= 0; i--) {
            idx[i] = idxMax[i];
        }
        incIdx(idx, c, idxMax);
    }

    private static int[] getIdxMax(int[] idx, long[] c, long n) {
        int[] max = new int[idx.length];
        for (int i = 0; i < idx.length; i++) {
            max[i] = (int) (n / c[i]);
        }
        return max;
    }

    private static void incIdx(int[] idx, long[] c, int[] idxMax, long incBy) {
        //incBy must be > 0
        if (idx[0] < idxMax[0]) {
            if (((idxMax[0] - idx[0]) * c[0]) < incBy) {
                idx[0] = idxMax[0];
            } else {
                if ((incBy / c[0]) < 1) {
                    idx[0]++;
                } else {
                    idx[0] += (incBy / c[0]);
                }
            }
        } else {
            incIdx(idx, c, idxMax);
        }
    }

    private static void incIdx(int[] idx, long[] c, int[] idxMax) {
        int col = 0;
        while (col < c.length) {
            if (idx[col] < idxMax[col]) {
                idx[col]++;
                break;
            } else {
                idx[col] = 0;
                col++;
            }
        }
        if (col > curCol) {
            curCol = col;
        }
    }

    private static void incIdxBy(int[] idx, long[] c, int[] idxMax, long incBy) {
        int col = 0;
        while (col < c.length && incBy > 0) {
            if (((idxMax[col] - idx[col]) * c[col]) < incBy) {
                long shift = idx[col] * c[col] - idx[col + 1] * c[col + 1];
                if ((shift > 0)) {
                    System.out.println("STOP");
                }
                incBy += shift;
                idx[col] = idxMax[col];
                incIdx(idx, c, idxMax);
                incIdxBy(idx, c, idxMax, incBy);
            } else {
                idx[col] += (incBy / c[col]) < 1 ? 1 : (incBy / c[0]);
                break;
            }

            if (idx[col] < idxMax[col]) {
                idx[col]++;
                break;
            } else {
                idx[col] = 0;
                col++;
            }
        }
        if (col > curCol) {
            curCol = col;
        }
    }

    private static void skipFirstCol(int[] idx, long[] c, int[] idxMax) {
        idx[0] = idxMax[0];
        incIdx(idx, c, idxMax);
    }

    private static long getTotal(int[] idx, long[] c) {
        long total = 0;
        for (int i = 0; i < c.length; i++) {
            total += c[i] * idx[i];
        }
        return total;
    }

    //		for (int i = idx.length - 1; i >= 0; i--) {
//	private static int getCurCol(int[] idx) {
//			if (idx[i] != 0) {
//				return i;
//			}
//		}
//		return 0;
//	}

}
