package edu.project2;

import java.util.NoSuchElementException;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    private int num;

    public UnionFind(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Expected n > 0");
        }
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i; // root of self
            rank[i] = 1; // contains only self
        }

        num = n;
    }

    public int find(int i) {
        if (i < 0 || i > parent.length) {
            throw new NoSuchElementException("Invalid element");
        }
        return root(i);
    }

    public int union(int u, int v) {
        int u1 = u;
        u1 = find(u1);
        int v1 = v;
        v1 = find(v1);
        if (u1 == v1) {
            return u1; // no-op
        }

        if (rank[v1] < rank[u1]) {
            int t = v1;
            v1 = u1;
            u1 = t;
        }
        parent[u1] = v1;
        rank[v1] += rank[u1];
        rank[u1] = -1;
        num--;
        return v1;
    }

    public int numberOfSets() {
        return num;
    }

    private int root(int u) {
        int u1 = u;
        while (parent[u1] != u1) {
            u1 = parent[u1];
        }
        return u1;
    }

    public int rank(int u) {
        int u1 = u;
        u1 = root(u1);
        return rank[u1];
    }
}
