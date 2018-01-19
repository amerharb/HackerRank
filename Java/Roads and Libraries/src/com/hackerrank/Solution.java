package com.hackerrank;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {

    static int roadsAndLibraries(int nCities, int nLib, int nRoad, int[][] cities) {

//        if (nRoad >= nLib) {
//            return nCities * nLib;
//        } else {
//            List<List<Integer>> islands = getConnectedGraphs(nCities, cities);
//            int cost = 0;
//            for (int i = 0; i < islands.size(); i++) {
//                cost += nLib + ((islands.get(i).size() - 1) * nRoad);
//            }
//            return cost;
//        }

        if (nRoad >= nLib) {
            return nCities * nLib;
        } else {
            Map<Integer, Vertex> vertices = getVertices(nCities, cities);

            int[] islands = getGraphIslands(vertices);
            //int[] islands = getIslandsStatistic(nCities, cities);
            int ic = islands.length;
            int ec = 0;
            for (int i = 0; i < islands.length; i++) {
                ec += islands[i] - 1;
            }
            return (ic * nLib) + (ec * nRoad);
        }
    }

    private static int[] getGraphIslands(Map<Integer, Vertex> vertexMap) {
        int tourCount = 0;
        List<Integer> islands = new ArrayList<>();

        for (Vertex v : vertexMap.values()) {
            if (v.TourNo == 0) {
                tourCount++;
                v.TourNo = tourCount;
                AtomicInteger cityCounter = new AtomicInteger(1); //start with value one becouse this is here the first city visited
                for (Vertex adj : v.adjacencies) {
                    travelTo(adj, tourCount, cityCounter);
                }
                islands.add(cityCounter.get());
            }
        }

        int[] islandArr = new int[islands.size()];
        for (int i = 0; i < islands.size(); i++) {
            islandArr[i] = islands.get(i).intValue();
        }
        return islandArr;
    }

    private static void travelTo(Vertex v, int tourCount, AtomicInteger cityCounter) {
        if (v.TourNo == 0) {
            v.TourNo = tourCount;
            cityCounter.incrementAndGet();
            for (Vertex adj : v.adjacencies) {
                travelTo(adj, tourCount, cityCounter);
            }
        }
    }

    private static Map<Integer, Vertex> getVertices(int nCities, int[][] cities) {

        Map<Integer, Vertex> map = new HashMap<>();
        for (int i = 1; i <= nCities; i++) {
            map.put(i, new Vertex(i));
        }

        for (int[] city : cities) {
            map.get(city[0]).adjacencies.add(map.get(city[1]));
            map.get(city[1]).adjacencies.add(map.get(city[0]));
        }
        return map;
    }

//    private static int[] getIslandsStatistic(int n, int[][] cities) {
//        int[] visitedCity = new int[n];
//        int tourCount = 0;
//        List<Integer> islands = new ArrayList<>();
//
//        for (int i = 0; i < n; i++) {
//            if (visitedCity[i] == 0) { //city never visited
//                tourCount++;
//                visitedCity[i] = tourCount;
//                AtomicInteger cityCounter = new AtomicInteger(1); //start with value one becouse this is here the first city visited
//                //try to visit all related
//                for (int j = 0; j < cities.length; j++) {
//                    if (i == cities[j][0] - 1) {
//                        travelTo(cities, visitedCity, cities[j][1] - 1, tourCount, cityCounter);
//                    }
//                    if (i == cities[j][1] - 1) {
//                        travelTo(cities, visitedCity, cities[j][0] - 1, tourCount, cityCounter);
//                    }
//                }
//                islands.add(cityCounter.get());
//            }
//        }
//
//        int[] islandArr = new int[islands.size()];
//        for (int i = 0; i < islands.size(); i++) {
//            islandArr[i] = islands.get(i).intValue();
//        }
//        return islandArr;
//    }
//
//    private static void travelTo(int[][] cities, int[] visitedCity, int c, int tourCount, AtomicInteger cityCounter) {
//        try {
//            if (visitedCity[c] == 0) {
//                visitedCity[c] = tourCount;
//                cityCounter.incrementAndGet();
//                //try to visit all related
//                for (int j = 0; j < cities.length; j++) {
//                    if (c == cities[j][0] - 1) {
//                        travelTo(cities, visitedCity, cities[j][1] - 1, tourCount, cityCounter);
//                    }
//                    if (c == cities[j][1] - 1) {
//                        travelTo(cities, visitedCity, cities[j][0] - 1, tourCount, cityCounter);
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//    }
//

    //
//    private static List<List<Integer>> getConnectedGraphs(int nCities, int[][] Roads) {
//        //convert array to Lists
//        List<List<Integer>> list = new ArrayList<>();
//        for (int[] road : Roads) {
//            List<Integer> twoCities = new ArrayList<>();
//            twoCities.add(road[0]);
//            twoCities.add(road[1]);
//            list.add(twoCities);
//
//        }
//
//        for (int i = 0; i < list.size(); ) {
//            List<List> removeList = new ArrayList<>();
//            for (int j = i + 1; j < list.size(); j++) {
//                if (whenFoundMerge(list.get(i), list.get(j))) {
//                    removeList.add(list.get(j));
//                }
//            }
//            if (removeList.size() > 0) {
//                list.removeAll(removeList);
//            } else {
//                i++;
//            }
//        }
//
//        //remove empty list from main list
////        List<List> removeList = new ArrayList<>();
////        for (int i = 0; i < list.size(); i++) {
////            if (list.get(i).isEmpty()) {
////                removeList.add(list.get(i));
////            }
////        }
////        list.removeAll(removeList);
//        return list;
//    }
//
//    private static boolean whenFoundMerge(List<Integer> l1, List<Integer> l2) {
//        for (Integer e : l2) {
//            if (l1.contains(e)) {
//                l2.removeAll(l1); //remove duplicated
//                l1.addAll(l2);
//                l2.clear();
//                return true;
//            }
//        }
//        return false;
//    }

//    private static int[][] getIslands(int n, int[][] cities) {
//        int[] visitedCity = new int[n];
//        int tourCount = 0;
//        int islands = 0;
//        for (int i = 0; i < n; i++) {
//            if (visitedCity[i] == 0) { //city never visited
//                tourCount++;
//                visitedCity[i] = tourCount;
//                islands++;
//                //try to visit all related
//                for (int j = 0; j < cities.length; j++) {
//                    if (i == cities[j][0] - 1) {
//                        travelTo(cities, visitedCity, cities[j][1] - 1, tourCount);
//                    }
//                    if (i == cities[j][1] - 1) {
//                        travelTo(cities, visitedCity, cities[j][0] - 1, tourCount);
//                    }
//                }
//            }
//        }
//
//        int[][] islandArr = new int[islands][];
//        int c = 0;
//        for (int i = 0; i < islands; i++) {
//            List<Integer> list = new ArrayList<>();
//            for (int j = 0; j < visitedCity.length; j++) {
//                if (visitedCity[j] == i + 1) {
//                    list.add(j + 1);
//                }
//            }
//            islandArr[i] = new int[list.size()];
//            for (int j = 0; j < list.size(); j++) {
//                islandArr[i][j] = list.get(j).intValue();
//            }
//        }
//        return islandArr;
//    }
//
//    private static void travelTo(int[][] cities, int[] visitedCity, int c, int tourCount) {
//        if (visitedCity[c] == 0) {
//            visitedCity[c] = tourCount;
//            //try to visit all related
//            for (int j = 0; j < cities.length; j++) {
//                if (c == cities[j][0] - 1) {
//                    travelTo(cities, visitedCity, cities[j][1] - 1, tourCount);
//                }
//                if (c == cities[j][1] - 1) {
//                    travelTo(cities, visitedCity, cities[j][0] - 1, tourCount);
//                }
//            }
//        }
//    }

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

class Vertex {
    int ID;
    Set<Vertex> adjacencies = new HashSet<>();
    public int TourNo = 0;

    Vertex(int ID) {
        this.ID = ID;
    }
}