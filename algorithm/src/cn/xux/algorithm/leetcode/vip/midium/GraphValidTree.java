package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 261.以图判树
 * 给定从 0 到 n-1 标号的 n 个结点，和一个无向边列表（每条边以结点对来表示），
 * 请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。
 *
 * 示例 1：
 * 输入: n = 5, 边列表 edges = [[0,1], [0,2], [0,3], [1,4]]
 * 输出: true
 *
 * 示例 2:
 * 输入: n = 5, 边列表 edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * 输出: false
 * 注意：你可以假定边列表 edges 中不会出现重复的边。
 * 由于所有的边是无向边，边 [0,1] 和边 [1,0] 是相同的，
 * 因此不会同时出现在边列表 edges 中。
 */
public class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];
        for(int i=1;i<n;i++) {
            parent[i] = i;
        }
        for(int[] edge : edges) {
            int px = find(parent, edge[0]);
            int py = find(parent, edge[1]);
            if(px==py) {
                return false;
            }
            union(parent, px, py);
        }
        for(int i=1;i<n;i++) {
            if(find(parent, i)!=find(parent, i-1)) {
                return false;
            }
        }
        return true;
    }

    private int find(int[] parent, int x) {
        if(parent[x]!=x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }

}
