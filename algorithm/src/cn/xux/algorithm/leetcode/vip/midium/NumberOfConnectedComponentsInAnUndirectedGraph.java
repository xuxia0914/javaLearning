package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.DSU;

/**
 * 323.无向连通图中的连通分量个数
 * 给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），
 * 请编写一个函数来计算无向图中连通分量的数目。
 *
 * 示例 1:
 * 输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]
 *  0          3
 *  |          |
 *  1 --- 2    4
 * 输出: 2
 *
 * 示例 2:
 * 输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *
 *  0           4
 *  |           |
 *  1 --- 2 --- 3
 * 输出: 1
 *
 * 注意:
 * 你可以假设在 edges 中不会出现重复的边。
 * 而且由于所以的边都是无向边，[0, 1] 与 [1, 0] 相同，
 * 所以它们不会同时在 edges 中出现。
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        if(n<2) {
            return n;
        }
        DSU dsu = new DSU(n);
        for(int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }
        int ans = 0;
        for(int i=0;i<n;i++) {
            if(i==dsu.find(i)) {
                ans++;
            }
        }
        return ans;
    }

}
