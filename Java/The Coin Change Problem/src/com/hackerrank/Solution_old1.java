package com.hackerrank;

// https://www.hackerrank.com/challenges/coin-change/problem

import java.util.*;

public class Solution_old1 {

	static long getWays(long n, long[] c) {
		long ways = 0;

		for (int numberOfCoins = 1; numberOfCoins <= c.length; numberOfCoins++) {
			long[][] arr = getArrayCombination(c, numberOfCoins);
			ROW:
			for (long[] ar : arr) {
				if (getArrayElimantsSum(ar) <= n) {
					//calc max time of use the coin
					int[] maxUse = new int[ar.length];
					for (int i = 0; i < ar.length; i++) {
						maxUse[i] = (int) (n / ar[i]);
//						for (int j = 1; n < j * ar[i]; j++) {
//							maxUse[i]++;
//						}
					}

					//generate array of all possiable way
					int[][] possComb = new int[getArrayElimantsSumInt(maxUse)][3];
					int x = 0;
					for (int i = 0; i < ar.length; i++) {
						for (int j = 1; j <= maxUse[i]; j++) {
							possComb[x][0] = (int) ar[i];
							possComb[x][1] = j;
							possComb[x][2] = possComb[x][0] * possComb[x][1];
							x++;
						}
					}

					int[][] indexComb = getArrayOfIndexCombination(possComb.length);
					for (int i = 0; i < indexComb.length; i++) {
						long total = 0;
						for (int j = 0; j < indexComb[i].length; j++) {
							total += possComb[indexComb[i][j]][2];
						}
						if (total == n) {
							ways++;
						}
					}
				}
			}
		}
		return ways;
	}

	static long[][] getArrayCombination(long[] arr, int k) {

		int pComb = getCombination(arr.length, k);

		long[][] r = new long[pComb][k];

		int[] s = new int[k];
		for (int i = 0; i < k; i++) {
			s[i] = i;
		}// generate first indicate array
		for (int l = 0; l < k; l++) {
			r[0][l] = arr[s[l]];
		}

		for (int j = 1; j < pComb; j++) {
			int i;
			for (i = k - 1; i >= 0 && s[i] == arr.length - k + i; i--) ;
			if (i < 0) {
				break;
			}

			s[i]++;                    // increment this item
			for (i++; i < k; i++) {    // fill up remaining items
				s[i] = s[i - 1] + 1;
			}

			for (int l = 0; l < k; l++) {
				r[j][l] = arr[s[l]];
			}


		}
		return r;
	}

	static int getCombination(int n, int k) {
		if (k > n) {
			return 0;
		} else if (k == 0) {
			return 1;
		} else {
			return getLimitedFactorial(n, k + 1) / getLimitedFactorial((n - k), 1);
		}
	}

	private static int getLimitedFactorial(int s, int e) {
		if (s < e) {
			return 1;
		} else if (s == e) {
			return s;
		} else {
			return s * getLimitedFactorial(s - 1, e);
		}
	}

	static int[][] getArrayOfIndexCombination(int numberOfElements) {
		int[][][] r = new int[numberOfElements + 1][][];

		for (int i = 0; i <= numberOfElements; i++) {
			int pComb = getCombination(numberOfElements, i);
			r[i] = getArrayOfIndexCombinationForK(numberOfElements, i);
		}

		return get2DArrayFrom3D(r);
	}

	static int[][] get2DArrayFrom3D(int[][][] arr) {
		int[][] r;
		int c = 0;
		//TODO: check if the array length is enough
		int[][] temp = new int[Math.max(arr.length * arr[0].length, 1000)][];//estimation can be increase
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				temp[c] = arr[i][j];
				c++;
			}
		}
		r = new int[c][];
		System.arraycopy(temp, 0, r, 0, c);
		return r;
	}

	static int[][] getArrayOfIndexCombinationForK(int n, int k) {
		List<int[]> subsets = new ArrayList<>();

		int[] s = new int[k];// here we'll keep indices
		// pointing to elements in input array

		if (k <= 0) {
			return new int[1][0];
		} else if (k <= n) {
			// first index sequence: 0, 1, 2, ...
			for (int i = 0; (s[i] = i) < k - 1; i++) ;
			subsets.add(getArrayCopy(s));
			for (; ; ) {
				int i;
				// find position of item that can be incremented
				for (i = k - 1; i >= 0 && s[i] == n - k + i; i--) ;
				if (i < 0) {
					break;
				}
				s[i]++;                    // increment this item
				for (++i; i < k; i++) {    // fill up remaining items
					s[i] = s[i - 1] + 1;
				}
				subsets.add(getArrayCopy(s));
			}
			int[][] r = new int[subsets.size()][];
			int i = 0;
			for (int[] a : subsets) {
				r[i] = a;
				i++;
			}
			return r;
		}
		return null;
	}

	static int[] getArrayCopy(int[] arr) {
		int[] a = new int[arr.length];
		System.arraycopy(arr, 0, a, 0, a.length);
		return a;
	}

	static long getArrayElimantsSum(long[] arr) {
		long s = 0;
		for (long l : arr) {
			s += l;
		}
		return s;
	}

	static int getArrayElimantsSumInt(int[] arr) {
		int s = 0;
		for (int i : arr) {
			s += i;
		}
		return s;
	}

	public static void main(String[] args) {
//		System.out.println(getLimitedFactorial(4, 5));
//		System.out.println(getCombination(10, 3));
//
//		long[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
//		//long[][] r = getArrayCombination(arr, 2);
//		int[][] r = getArrayOfIndexCombination(9);
//		for (int[] ro : r) {
//			for (int roo : ro) {
//				System.out.print(roo + ", ");
//			}
//			System.out.println();
//		}
//		int rr[][] = getArrayOfIndexCombination(9);
//
//		System.exit(0);

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
}
