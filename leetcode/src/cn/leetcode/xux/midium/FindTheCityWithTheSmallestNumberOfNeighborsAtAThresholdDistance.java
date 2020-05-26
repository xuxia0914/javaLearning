package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 1334. 阈值距离内邻居最少的城市
 * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，
 * 其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，
 * 距离阈值是一个整数 distanceThreshold。
 * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。
 * 如果有多个这样的城市，则返回编号最大的城市。
 * 注意，连接城该路市 i 和 j 的路径的距离等于沿径的所有边的权重之和。
 *
 * 示例 1：
 * 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * 输出：3
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
 * 城市 0 -> [城市 1, 城市 2]
 * 城市 1 -> [城市 0, 城市 2, 城市 3]
 * 城市 2 -> [城市 0, 城市 1, 城市 3]
 * 城市 3 -> [城市 1, 城市 2]
 * 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
 *
 * 示例 2：
 * 输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * 输出：0
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
 * 城市 0 -> [城市 1]
 * 城市 1 -> [城市 0, 城市 4]
 * 城市 2 -> [城市 3, 城市 4]
 * 城市 3 -> [城市 2, 城市 4]
 * 城市 4 -> [城市 1, 城市 2, 城市 3]
 * 城市 0 在阈值距离 4 以内只有 1 个邻居城市。
 *
 * 提示：
 * 2 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti, distanceThreshold <= 10^4
 * 所有 (fromi, toi) 都是不同的。
 */
public class FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance {

    public static void main(String[] args) {
        System.out.println(
                new FindTheCityWithTheSmallestNumberOfNeighborsAtAThresholdDistance()
                        .findTheCity(6, new int[][]{{0,3,7},{2,4,1},{0,1,5},{2,3,10},{1,3,6},{1,2,1}}, 417)
        );
    }

    //Floyd-Warshall算法（Floyd-Warshall algorithm）是解决任意两点间的最短路径的一种算法
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        for(int[] arr : dp) {
            Arrays.fill(arr, 10001);
        }
        for(int[] edge : edges) {
            dp[edge[0]][edge[1]] = edge[2];
            dp[edge[1]][edge[0]] = edge[2];
        }
        for(int k=0;k<n;k++) {
            for(int i=0;i<n;i++) {
                for(int j=i+1;j<n;j++) {
                    if(i!=k&&j!=k) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);
                        dp[j][i] = Math.min(dp[j][i], dp[i][k]+dp[k][j]);
                    }
                }
            }
        }
        int cnt = n;
        int ans = 0;
        for(int i=0;i<n;i++) {
            int currCnt = 0;
            for(int j=0;j<n;j++) {
                if(i!=j&&dp[i][j]<=distanceThreshold) {
                    currCnt++;
                }
            }
            if(currCnt<=cnt) {
                cnt = currCnt;
                ans = i;
            }
        }
        return ans;
    }

    List<int[]>[] graph;
    Map<Integer, Integer>[] canGet;

    public int findTheCity1(int n, int[][] edges, int distanceThreshold) {
        canGet = new Map[n];
        graph = new List[n];
        for(int[] edge : edges) {
            if(graph[edge[0]]==null) {
                graph[edge[0]] = new ArrayList<>();
            }
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            if(graph[edge[1]]==null) {
                graph[edge[1]] = new ArrayList<>();
            }
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }
        for(int i=0;i<n;i++) {
            boolean[] visited = new boolean[n];
            canGet[i] = new HashMap<>();
            dfs(i, i, 0, graph, visited, distanceThreshold);
        }
        int ans = 0;
        for(int i=1;i<n;i++) {
            if(canGet[i].size()<=canGet[ans].size()) {
                ans = i;
            }
        }
        return ans;
    }

    private void dfs(int startCity, int currCity, int currDis,
                     List<int[]>[] graph, boolean[] visited,
                     int distanceThreshold) {
        if(currDis>distanceThreshold||visited[currCity]||
                (canGet[startCity].containsKey(currCity)&&currDis>=canGet[startCity].get(currCity))) {
            return;
        }
        canGet[startCity].put(currCity, currDis);
        List<int[]> neis = graph[currCity];
        visited[currCity] = true;
        if(neis!=null) {
            for(int[] nei : neis) {
                dfs(startCity, nei[0], currDis+nei[1],
                        graph, visited, distanceThreshold);
            }
        }
        visited[currCity] = false;
    }

}
