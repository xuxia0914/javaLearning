package cn.xux.algorithm.common;

/**
 * 并查集
 */
public class DSU {

    int[] parent;

    public DSU(int N) {
        parent = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(y)] = find(x);
    }

}
