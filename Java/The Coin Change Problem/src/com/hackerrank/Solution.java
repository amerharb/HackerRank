package com.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Amer on 02-Jan-18.
 */
public class Solution {

	private int col;//current column working
	private static int curCol = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		long[] c = new long[m];
		for (int c_i = 0; c_i < m; c_i++) {
			c[c_i] = in.nextLong();
		}
		// Print the number of ways of making change for 'n' units using coins having the values given by 'c'
		long ways = getWays(n, c);
		System.out.println(ways);
	}

	static long getWays(long n, long[] c) {
		long ways = 0;

		Arrays.sort(c);
		int[] idx = new int[c.length];
		int[] idxMax = getIdxMax(idx, c, n);

		while (curCol < c.length) {
			long total = getTotal(idx, c);
			if (total < n) {
				incIdx(idx, c, idxMax);
			} else if (total == n) { //found total
				ways++;
				skipFirstCol(idx, c, idxMax);

			} else { // total > n
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

	private static void skipFirstCol(int[] idx, long[] c, int[] idxMax) {
		idx[0] = idxMax[0];
		incIdx(idx, c, idxMax);

		/*
		idx[0] = 0;
		int col = 1;
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
*/
	}

	private static long getTotal(int[] idx, long[] c) {
		long total = 0;
		for (int i = 0; i < c.length; i++) {
			total += c[i] * idx[i];
		}
		return total;
	}

//	private static int getCurCol(int[] idx) {
//		for (int i = idx.length - 1; i >= 0; i--) {
//			if (idx[i] != 0) {
//				return i;
//			}
//		}
//		return 0;
//	}

}
