package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 1029. 寻找最便宜的航行旅途（最多经过k个中转站）
 * 中文English
 * 有n个城市被一些航班所连接。每个航班 (u,v,w) 从城市u出发，到达城市v，价格为w。
 * 给定城市数目 n，所有的航班flights。你的任务是找到从起点src到终点站dst的最便宜线路的价格，而旅途中最多只能中转K次。
 * 如果没有找到合适的线路，返回 -1。
 *
 * 样例
 * 样例 1:
 * 输入:
 *   n = 3,
 *   flights = [[0,1,100],[1,2,100],[0,2,500]],
 *   src = 0, dst = 2, K = 0
 * 输出: 500
 * 样例 2:
 *
 * 输入:
 *   n = 3,
 *   flights = [[0,1,100],[1,2,100],[0,2,500]],
 *   src = 0, dst = 2, K = 1
 * 输出: 200
 * 注意事项
 * 总城市数 n 在 1-100 之间，每个城市被标号为 0 到 n-1。
 * 航线的总数在 0 到 n * (n - 1) / 2 之间
 * 每条航线会被以 [出发站，终点站，价格] 的形式展现。
 * 每条航线的价格都在 1-10000之间。
 * 中转站的总数限制范围为 0 到 n-1 之间。
 * 不会有重复或者自环航线出现
 */
public class Lintcode1029 {

    public static void main(String[] args) {
        System.out.println(new Lintcode1029().findCheapestPrice(
                10,
                new int[][]{{3,4,4},{2,5,6},{4,7,10},{9,6,5},{7,4,4},{6,2,10},{6,8,6},{7,9,4},{1,5,4},{1,0,4},{9,7,3},{7,0,5},{6,5,8},{1,7,6},{4,0,9},{5,9,1},{8,7,3},{1,2,6},{4,1,5},{5,2,4},{1,9,1},{7,8,10},{0,4,2},{7,2,8}},
                6,0,7
        ));
    }

    /**
     * @param n: a integer
     * @param flights: a 2D array
     * @param src: a integer
     * @param dst: a integer
     * @param K: a integer
     * @return: return a integer
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // write your code here
        if(src==dst) {
            return 0;
        }
        List<int[]>[] graph = new List[n];
        for(int[] flight : flights) {
            if(graph[flight[0]]==null) {
                graph[flight[0]] = new ArrayList<>();
            }
            graph[flight[0]].add(new int[]{flight[1], flight[2]});
        }
        int[] dp = new int[n];
        int[] ans = new int[n];
        Arrays.fill(dp, -1);
        Arrays.fill(ans, -1);
        dp[src] = 0;
        ans[src] = 0;
        for(int k=0;k<=K;k++) {
            int[] newDp = new int[n];
            Arrays.fill(newDp, -1);
            for(int i=0;i<n;i++) {
                if(dp[i]!=-1&&graph[i]!=null) {
                    for(int[] next : graph[i]) {
                        int np = next[0];
                        int nw = next[1]+dp[i];
                        if(ans[np]==-1||nw<ans[np]) {
                            newDp[np] = nw;
                            ans[np] = nw;
                        }
                    }
                }
            }
            dp = newDp;
        }
        return ans[dst];
    }

}
