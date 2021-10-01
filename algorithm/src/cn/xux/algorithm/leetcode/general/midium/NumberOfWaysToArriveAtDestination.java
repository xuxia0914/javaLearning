package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 1976. 到达目的地的方案数
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，
 * 某些路口之间有 双向 道路。输入保证你可以从任意路口出发到达其他任意路口，
 * 且任意两个路口之间最多有一条路。
 *
 * 给你一个整数 n 和二维整数数组 roads ，
 * 其中 roads[i] = [ui, vi, timei] 表示
 * 在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。
 * 你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
 *
 * 请返回花费 最少时间 到达目的地的 路径数目 。
 * 由于答案可能很大，将结果对 109 + 7 取余 后返回。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * 输出：4
 * 解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
 * 四条花费 7 分钟的路径分别为：
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 * 示例 2：
 *
 * 输入：n = 2, roads = [[1,0,10]]
 * 输出：1
 * 解释：只有一条从路口 0 到路口 1 的路，花费 10 分钟。
 *
 *
 * 提示：
 *
 * 1 <= n <= 200
 * n - 1 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * 1 <= timei <= 109
 * ui != vi
 * 任意两个路口之间至多有一条路。
 * 从任意路口出发，你能够到达其他任意路口。
 */
public class NumberOfWaysToArriveAtDestination {

    public int countPaths(int n, int[][] roads) {
        if(n==1) {
            return 1;
        }
        // graph[i] 表示所有与i相邻的 {节点， 距离}
        List<int[]>[] graph = new List[n];
        // 初始化 graph
        for(int[] road : roads) {
            if(graph[road[0]]==null) {
                graph[road[0]] = new ArrayList<>();
            }
            graph[road[0]].add(new int[]{road[1], road[2]});
            if(graph[road[1]]==null) {
                graph[road[1]] = new ArrayList<>();
            }
            graph[road[1]].add(new int[]{road[0], road[2]});
        }
        // lens[i]表示节点i距离节点0的最小距离
        long[] lens = new long[n];
        Arrays.fill(lens, Long.MAX_VALUE);
        lens[0] = 0;
        // ans[i]表示从0到节点i距离最近的路径数
        long[] ans = new long[n];
        ans[0] = 1;
        // 遍历顺序要按照从节点0到当前节点的距离排序很重要
        // 能保证遍历到节点i时，所有的 距离最小的从0到i的情况 都已经遍历过了，即此时ans[i]已经达到最大不会再增加了
        PriorityQueue<Edge> q1 = new PriorityQueue<>(Comparator.comparingLong(o->o.d));
        q1.offer(new Edge(0, 0));
        // visited[i]表示节点i是否已经被访问过
        boolean[] visited = new boolean[n];
        while(q1.size()>0) {
            Edge curr = q1.poll();
            if(visited[curr.p]) {
                continue;
            }
            visited[curr.p] = true;
            for(int[] nei : graph[curr.p]) {
                if(lens[curr.p]+nei[1]<lens[nei[0]]) {
                    lens[nei[0]] = lens[curr.p]+nei[1];
                    ans[nei[0]] = ans[curr.p];
                    q1.offer(new Edge(nei[0], lens[nei[0]]));
                }else if(lens[curr.p]+nei[1]==lens[nei[0]]) {
                    ans[nei[0]] = (ans[nei[0]]+ans[curr.p])%1000000007;
                }
            }
        }
        return (int)ans[n-1];
    }

    class Edge {

        // 节点号
        int p;
        // 从节点0到当前节点的距离
        long d;

        Edge(int point, long dist) {
            this.p = point;
            this.d = dist;
        }

    }

}
