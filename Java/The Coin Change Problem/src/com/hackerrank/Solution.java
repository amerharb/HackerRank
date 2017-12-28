package com.hackerrank;

/**
 * Created by Amer on 28-Dec-17.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
// https://www.hackerrank.com/challenges/coin-change/problem

public class Solution {

	static class CoinUse {
		long value;
		long use;
		final long total;

		CoinUse(long value, long use) {
			this.value = value;
			this.use = use;
			total = value * use;
		}

	}

	static class CoinUseList extends ArrayList<CoinUse> implements Comparator<CoinUseList> {
		long value;

		CoinUseList(long value) {
			this.value = value;
		}

		@Override
		public int compare(CoinUseList o1, CoinUseList o2) {
			if (o1.value > o2.value)
				return 1;
			else if (o1.value < o2.value)
				return -1;
			else
				return 0;
		}
	}

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

		List<CoinUseList> coinList = new ArrayList<>();
		for (long v : c) {
			CoinUseList useList = new CoinUseList(v);
			coinList.add(useList);
			for (long i = 0; n >= (i * v); i++) {
				useList.add(new CoinUse(v, i));
			}
		}

		int ser[] = new int[coinList.size()];
		long possTry = 1;
		int i = 0;
		for (List l : coinList) {
			possTry *= l.size();
			ser[i] = l.size();
			i++;
		}

		for (int j = 0; j < possTry; j++) {
			int[] nb = getNBArray(j, ser);
			long total = 0;
			for (int k = 0; k < nb.length; k++) {
				CoinUse cu = coinList.get(k).get(nb[k]);
				total += cu.total;
			}
			if (total == n) {
				ways++;
			}
		}

		return ways;
	}

	private static int[] getNBArray(int j, int[] ser) {
		int[] nb = new int[ser.length];
		for (int i = nb.length - 1; i >= 0 && j > 0; i--) {
			int max = getMaxSer(i, ser);
			nb[i] = j / max;
			j -= (nb[i] * max);
		}
		return nb;
	}

	private static int getMaxSer(int i, int[] ser) {
		int max = 1;
		for (i--; i >= 0; i--) {
			max *= ser[i];
		}
		return max;
	}

}

