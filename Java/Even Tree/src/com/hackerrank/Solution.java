package com.hackerrank;

import java.util.*;

public class Solution {
    static int evenTree(int n, int m, int[][] tree) {
        Map<Integer, Vertex> vs = getVertices(n, tree);
        return countMore(vs.get(1), null);
    }

    private static int countMore(Vertex root, Vertex origin) {
        int count = 0;
        for (Vertex v : root.adjacencies.values()) {
            if (v != origin) {
                if (v.childCount % 2 != 0) {
                    if (v.childCount == 1) {
                        count++;
                    } else {
                        count += 1 + countMore(v, root);
                    }
                } else {
                    count += countMore(v, root);
                }
            }
        }
        return count;
    }

    private static Map<Integer, Vertex> getVertices(int n, int[][] edges) {
        Map<Integer, Vertex> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new Vertex(i));
        }

        for (int[] edge : edges) {
            map.get(edge[0]).adjacencies.putIfAbsent(edge[1], map.get(edge[1]));
            map.get(edge[1]).adjacencies.putIfAbsent(edge[0], map.get(edge[0]));
        }

        //fill child Count
        map.get(1).countVertexFrom(null); //count for root then it will fill all
        return map;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] tree = new int[m][2];
        for (int tree_i = 0; tree_i < m; tree_i++) {
            for (int tree_j = 0; tree_j < 2; tree_j++) {
                tree[tree_i][tree_j] = in.nextInt();
            }
        }
        int result = evenTree(n, m, tree);
        System.out.println(result);
        in.close();
    }
}

class Vertex {
    int ID;
    Map<Integer, Vertex> adjacencies = new HashMap<>();
    int childCount;

    Vertex(int ID) {
        this.ID = ID;
    }

    int countVertexFrom(Vertex from) { //WARNING: it only work in tree structures infinite loop can be happened if there is circular relation
        int count = 0;
        for (Vertex adj : adjacencies.values()) {
            if (adj != from) {
                count += 1 + adj.countVertexFrom(this);
            }
        }
        childCount = count;
        return count;
    }
}
