package com.hackerrank;

import java.util.*;

public class Solution {
    static int evenTree(int n, int m, int[][] tree) {
        Map<Integer, Vertex> vs = getVertices(n, tree);
        int count = 0;
        for (Vertex v : vs.values()) {
            if (v.adjacencies.size() % 2 == 0) {
                //find at least another vertex with eve
                count++;
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
            map.get(edge[0]).adjacencies.add(map.get(edge[1]));
            map.get(edge[1]).adjacencies.add(map.get(edge[0]));
        }
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
    Set<Vertex> adjacencies = new HashSet<>();

    Vertex(int ID) {
        this.ID = ID;
    }

    int countVertexFrom(Vertex from) {
        int count = 0;
        for (Vertex adj : adjacencies) {
            if (from != adj) {
                count += 1 + adj.countVertexFrom(this);
            }
        }
        return count;
    }
}
