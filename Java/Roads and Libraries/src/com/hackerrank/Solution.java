package com.hackerrank;

import java.util.*;

public class Solution {

    static int roadsAndLibraries(int n, int nLib, int nRoad, int[][] cities) {
        if (nRoad >= nLib) {
            return n * nLib;
        } else {
            int islands = getIslands(n, cities);
            return islands;
        }
    }

    private static int getIslands(int n, int[][] cities) {
        boolean[] visitedCity = new boolean[n];
        int islands = 0;
        for (int i = 0; i < n; i++) {
            if (!visitedCity[i]) {
                visitedCity[i] = true;
                islands++;
                //try to visit all related
                for (int j = 0; j < cities.length; j++) {
                    if (i == cities[j][0]-1) {
                        travelTo(cities ,visitedCity, cities[j][1]-1);
                    }
                    if (i == cities[j][1]-1) {
                        travelTo(cities, visitedCity, cities[j][0]-1);
                    }
                }
            }
        }
        return islands;
    }

    private static void travelTo(int[][] cities, boolean[] visitedCity, int c) {
        if (!visitedCity[c]) {
            visitedCity[c] = true;
            //try to visit all related
            for (int j = 0; j < cities.length; j++) {
                if (c == cities[j][0]-1) {
                    travelTo(cities ,visitedCity, cities[j][1]-1);
                }
                if (c == cities[j][1]-1) {
                    travelTo(cities, visitedCity, cities[j][0]-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int c_lib = in.nextInt();
            int c_road = in.nextInt();
            int[][] cities = new int[m][2];
            for (int cities_i = 0; cities_i < m; cities_i++) {
                for (int cities_j = 0; cities_j < 2; cities_j++) {
                    cities[cities_i][cities_j] = in.nextInt();
                }
            }
            int result = roadsAndLibraries(n, c_lib, c_road, cities);
            System.out.println(result);
        }
        in.close();
    }
}
