package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 1395. 树的重心
 * 中文English
 * 对于一棵多叉树，如果有一个结点 R，以R为根，其所有子树的最大子树的结点数最少，则称结点 R 为这棵树的重心。
 * 现在给你一棵有 n 个结点的多叉树，求这棵树的重心，如果有多个重心，则返回编号最小的。
 * x[i], y[i]代表第 i 条边的两个点。
 *
 * 样例
 * 样例 1:
 *
 * 给出 x = `[1]`, y = `[2]`, 返回 `1`。
 * 输入:
 * [1]
 * [2]
 * 输出:
 * 1
 *
 * 解释：
 * 1 和 2 都可以为重心，但是 1 的编号最小。
 * 样例 2:
 *
 * 给出 x = `[1,2,2]`, y = `[2,3,4]`, 返回 `2`。
 * 输入:
 * [1,2,2]
 * [2,3,4]
 * 输出:
 * 2
 *
 * 解释：
 * 2 为重心。
 * 注意事项
 * 2 <= n <= 10^5
 * 1 <= x[i], y[i] <= n
 */
public class Lintcode1395 {

    /**
     * @param x: The vertexes of the edges
     * @param y: The vertexes of the edges
     * @return: Return the index of barycentre
     */
    public int getBarycentre(int[] x, int[] y) {
        // Write your code here
        if(x==null||x.length==0) {
            return 0;
        }
       int n = x.length+1;
        graph = new List[n+1];
        for(int i=1;i<=n;i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<x.length;i++) {
            graph[x[i]].add(y[i]);
            graph[y[i]].add(x[i]);
        }
        int ans = 0;
        int cnt = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++) {
            int maxCnt = 0;
            for(int nei : graph[i]) {
                maxCnt = Math.max(maxCnt, dfs(nei, i, n+1));
            }
            if(maxCnt<cnt) {
                ans = i;
                cnt = maxCnt;
            }
        }
        return ans;
    }

    List<Integer>[] graph;
    Map<Integer, Integer> mem = new HashMap<>();

    public int dfs(int curr, int parent, int n) {
        List<Integer> neis = graph[curr];
        if(neis.size()==1) {
            return 1;
        }
        int key = curr*n+parent;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        int ans = 0;
        for(int nei : neis) {
            if(nei!=parent) {
                ans += dfs(nei, curr, n);
            }
        }
        mem.put(key, ans+1);
        return ans+1;
    }

}
