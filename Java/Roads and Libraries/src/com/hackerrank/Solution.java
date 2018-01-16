package com.hackerrank;

import java.util.*;

public class Solution {

    static int roadsAndLibraries(int nCities, int nLib, int nRoad, int[][] cities) {

        int[][] islands = getIslands(nCities, cities);
        for (int i = 0; i < islands.length; i++) {
            System.out.print("island " + i + " : ");
            for (int j = 0; j < islands[i].length; j++) {
                System.out.print(islands[i][j] + ", ");
            }
            System.out.println();
        }
        return 0;

        //        if (nRoad >= nLib) {
//            return n * nLib;
//        } else {
//            int islands = getIslands(n, cities);
//            return islands;
//        }
    }

    private static int[][] getIslands(int n, int[][] cities) {
        int[] visitedCity = new int[n];
        int tourCount = 0;
        int islands = 0;
        for (int i = 0; i < n; i++) {
            if (visitedCity[i] == 0) { //city never visited
                tourCount++;
                visitedCity[i] = tourCount;
                islands++;
                //try to visit all related
                for (int j = 0; j < cities.length; j++) {
                    if (i == cities[j][0] - 1) {
                        travelTo(cities, visitedCity, cities[j][1] - 1, tourCount);
                    }
                    if (i == cities[j][1] - 1) {
                        travelTo(cities, visitedCity, cities[j][0] - 1, tourCount);
                    }
                }
            }
        }

        int[][] islandArr = new int[islands][];
        int c = 0;
        for (int i = 0; i < islands; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < visitedCity.length; j++) {
                if (visitedCity[j] == i + 1) {
                    list.add(j + 1);
                }
            }
            islandArr[i] = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                islandArr[i][j] = list.get(j).intValue();
            }
        }
        return islandArr;
    }

    private static void travelTo(int[][] cities, int[] visitedCity, int c, int tourCount) {
        if (visitedCity[c] == 0) {
            visitedCity[c] = tourCount;
            //try to visit all related
            for (int j = 0; j < cities.length; j++) {
                if (c == cities[j][0] - 1) {
                    travelTo(cities, visitedCity, cities[j][1] - 1, tourCount);
                }
                if (c == cities[j][1] - 1) {
                    travelTo(cities, visitedCity, cities[j][0] - 1, tourCount);
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
