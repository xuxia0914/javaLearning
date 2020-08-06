package cn.xux.algorithm.lintcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 752. 流浪剑客斯温
 * 中文English
 * 在物质位面“现实”中，有n+1个星球，分别为星球0，星球1，……，星球n。
 * 每一个星球都有一个传送门，通过传送门可以直接到达目标星球而不经过其他的星球。
 * 不过传送门有两个缺点。
 * 第一，从星球i通过传送门只能到达编号比i大，且与i的差不超过limit的星球。
 * 第二，通过传送门到达星球j，需要cost[j]个金币。
 * 现在，流浪剑客斯温到达星球0后身上有m个金币，请问他有多少种方法通过传送门到达星球n？
 *
 * 样例
 * 例1:
 * 输入:
 * n = 1
 * m = 1,
 * limit = 1
 * cost = [0, 1]
 * 输出:
 * 1
 * 解释:
 * 方案1：星球0→星球1
 * 例2:
 *
 * 输入:
 * n = 1
 * m = 1
 * limit = 1
 * cost = [0,2]
 * 输出:
 * 0
 * 解释:
 * 无合法方案
 * 注意事项
 * 1 <= n <= 50, 0 <= m <= 100, 1 <= limit <= 50, 0 <= cost[i] <= 100。
 * 由于cost[0]没有意义，题目保证cost[0] = 0。
 */
public class Lintcode752 {

    /**
     * @param n: the max identifier of planet.
     * @param m: gold coins that Sven has.
     * @param limit: the max difference.
     * @param cost: the number of gold coins that reaching the planet j through the portal costs.
     * @return: return the number of ways he can reach the planet n through the portal.
     */
    public long getNumberOfWays(int n, int m, int limit, int[] cost) {
        if(n==0) {
            return 1;
        }
        long[][] dp = new long[n+1][m+1];
        dp[0][m] = 1;
        for(int start=0;start<n;start++) {
            for(int coin=0;coin<=m;coin++) {
                if(dp[start][coin]>0) {
                    for(int tar=start+1;tar<=Math.min(start+limit,n);tar++) {
                        if(coin>=cost[tar]) {
                            dp[tar][coin-cost[tar]] += dp[start][coin];
                        }

                    }
                }
            }
        }
        long ans = 0;
        for(int i=0;i<=m;i++) {
            ans += dp[n][i];
        }
        return ans;
    }


    public long getNumberOfWays1(int n, int m, int limit, int[] cost) {
        //
        if(n==0) {
            return 1;
        }
        return dfs(0, n, limit, cost, m);
    }

    Map<Integer, Long> mem = new HashMap<>();

    private long dfs(int curr, int n, int limit, int[] cost, int m) {
        if(curr==n) {
            return 1;
        }
        int key = curr*101+m;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        long ans = 0;
        for(int i=1;i<=Math.min(n-curr, limit);i++) {
            if(m>=cost[curr+i]) {
                ans += dfs(curr+i, n, limit, cost, m-cost[curr+i]);
            }
        }
        mem.put(key, ans);
        return ans;
    }

}
