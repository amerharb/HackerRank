package com.hackerrank;

/**
 * Created by Amer on 28-Dec-17.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
// https://www.hackerrank.com/challenges/coin-change/problem

public class Solution_old2 {

	static class CoinUse implements Comparable<CoinUse> {
		long value;
		long use;
		final long total;

		CoinUse(long value, long use) {
			this.value = value;
			this.use = use;
			total = value * use;
		}

		@Override
		public int compareTo(CoinUse o) {
			if (this.value > o.value)
				return 1;
			else if (this.value < o.value)
				return -1;
			else if (this.use > o.use)
				return 1;
			else if (this.use < o.use)
				return -1;
			else
				return 0;
		}
	}

	static class CoinUseList extends ArrayList<CoinUse> implements Comparable<CoinUseList> {
		long value;

		CoinUseList(long value) {
			this.value = value;
		}

		@Override
		public int compareTo(CoinUseList o) {
			if (this.value > o.value)
				return 1;
			else if (this.value < o.value)
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

//		Comparator<CoinUse> coinUseComparator = new Comparator<CoinUse>() {
//			@Override
//			public int compare(CoinUse o1, CoinUse o2) {
//				//bigger value first
//				if (o1.value > o2.value)
//					return -1;
//				else if (o1.value < o2.value)
//					return 1;
//				else if (o1.use > o2.use)
//					return -1;
//				else if (o1.use < o2.use)
//					return 1;
//				else
//					return 0;
//			}
//		};
//
//		List<CoinUseList> coinList = new ArrayList<>();
//		for (long v : c) {
//			CoinUseList useList = new CoinUseList(v);
//			coinList.add(useList);
//			for (long i = 0; n >= (i * v); i++) {
//				useList.add(new CoinUse(v, i)); //38-61429-57
//			}
//			useList.sort(coinUseComparator);
//		}

		List<CoinUse> coinUseList= new ArrayList<>();
		for (long v : c) {
			for (long i = 0; n >= (i * v); i++) {
				coinUseList.add(new CoinUse(v, i));
			}
		}
		Comparator<CoinUse> coinUseTotalComparator= new Comparator<CoinUse>() {
			@Override
			public int compare(CoinUse o1, CoinUse o2) {
				//bigger value first
				if (o1.total > o2.total)
					return -1;
				else if (o1.total < o2.total)
					return 1;
				else
					return 0;
			}
		};
		coinUseList.sort(coinUseTotalComparator);

//		Comparator<CoinUseList> coinUseListComparator = new Comparator<CoinUseList>() {
//			@Override
//			public int compare(CoinUseList o1, CoinUseList o2) {
//				//biger value first
//				if (o1.value > o2.value)
//					return -1;
//				else if (o1.value < o2.value)
//					return 1;
//				else
//					return 0;
//			}
//		};
//
//		coinList.sort(coinUseListComparator);

//		int CoinUseCount = 0;
//		for (CoinUseList cul : coinList) {
//			CoinUseCount += cul.size();
//		}
//		CoinUse[] CoinUseArr = new CoinUse[CoinUseCount];
//		CoinUseCount = 0;
//		for (CoinUseList cul : coinList) {
//			for (CoinUse cu : cul) {
//				CoinUseArr[CoinUseCount++] = cu;
//			}
//		}

		//TRIAL:
		//int find = pairMe(0, coinUseList, 0, n);
		//System.out.println(find);
//		long ser[] = new long[coinList.size()];
//		long serMax[] = new long[coinList.size()];
//
////		long possTry = 1;
////		int i = 0;
////		serMax[0] = 1;
////		for (List cu : coinList) {
////			possTry *= cu.size();
////			ser[i] = cu.size();
////			if (i > 0)
////				serMax[i] = serMax[i - 1] * ser[i - 1];
////			i++;
////		}
////
////		for (int j = 0; j < possTry; j++) {
////			int[] nb = getNBArray(j, serMax);
////			long total = 0;
////			for (int k = 0; k < nb.length; k++) {
////				CoinUse cu = coinList.get(k).get(nb[k]);
////				total += cu.total;
////				if (total > n) {
////					break;
////				}
////			}
////			if (total == n) {
////				ways++;
////			}
////			if (j % 10_000_000 == 0)
////				System.out.println("10 Million, ways found so far: " + ways + " finish " + (float) j / possTry);
////		}

		return ways;
	}

	private static int pairMe(int index, ArrayList<CoinUse> list, long total, long target) {
		for (int i = index; i < list.size(); i++) {

		}
		return -1;
	}

	private static int[] getNBArray(int j, long[] ser) { //ser changed to serMax in calling
		int[] nb = new int[ser.length];
		for (int i = nb.length - 1; i >= 0 && j > 0; i--) {
//			int max = getMaxSer(i, ser);
			long max = ser[i];
			nb[i] = (int) ((long) j / max);
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
