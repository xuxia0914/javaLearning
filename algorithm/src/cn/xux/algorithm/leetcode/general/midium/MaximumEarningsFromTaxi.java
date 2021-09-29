package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 2008. 出租车的最大盈利
 * 你驾驶出租车行驶在一条有 n 个地点的路上。这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n ，通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向。
 *
 * 乘客信息用一个下标从 0 开始的二维数组 rides 表示，其中 rides[i] = [starti, endi, tipi] 表示第 i 位乘客需要从地点 starti 前往 endi ，愿意支付 tipi 元的小费。
 *
 * 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元。你同时 最多 只能接一个订单。
 *
 * 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。
 *
 * 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 5, rides = [[2,5,4],[1,5,1]]
 * 输出：7
 * 解释：我们可以接乘客 0 的订单，获得 5 - 2 + 4 = 7 元。
 * 示例 2：
 *
 * 输入：n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
 * 输出：20
 * 解释：我们可以接以下乘客的订单：
 * - 将乘客 1 从地点 3 送往地点 10 ，获得 10 - 3 + 2 = 9 元。
 * - 将乘客 2 从地点 10 送往地点 12 ，获得 12 - 10 + 3 = 5 元。
 * - 将乘客 5 从地点 13 送往地点 18 ，获得 18 - 13 + 1 = 6 元。
 * 我们总共获得 9 + 5 + 6 = 20 元。
 *
 *
 * 提示：
 *
 * 1 <= n <= 105
 * 1 <= rides.length <= 3 * 104
 * rides[i].length == 3
 * 1 <= starti < endi <= n
 * 1 <= tipi <= 105
 */
public class MaximumEarningsFromTaxi {

    public long maxTaxiEarnings(int n, int[][] rides) {
        if(n==1) {
            return 0;
        }
        // graph[i]表示以i为起点能到达的所有{终点，小费}的列表
        List<int[]>[] graph = new List[n+1];
        for(int[] ride : rides) {
            if(graph[ride[0]]==null) {
                graph[ride[0]] = new ArrayList<>();
            }
            graph[ride[0]].add(new int[]{ride[1], ride[2]});
        }
        // dp[i]表示以i为起点，n为终点时的最大收益
        long[] dp = new long[n+1];
        for(int i=n-1;i>0;i--) {
            dp[i] = dp[i+1];
            List<int[]> nexts = graph[i];
            if(nexts!=null&&nexts.size()>0) {
                for(int[] next : nexts) {
                    dp[i] = Math.max(dp[i], next[0]-i+next[1]+dp[next[0]]);
                }
            }
        }
        return dp[1];
    }

    public long maxTaxiEarnings1(int n, int[][] rides) {
        // dp[i]表示以1为起点，i为终点时的最大收益
        long[] dp = new long[n+1];
        Arrays.sort(rides, Comparator.comparingInt(a -> a[0]));
        int idx = 0;
        for (int i=1;i<=n;i++) {
            dp[i] = Math.max(dp[i], dp[i-1]);
            while(idx<rides.length&&rides[idx][0]==i) {
                int[] curr = rides[idx++];
                dp[curr[1]] = Math.max(dp[curr[1]], dp[curr[0]]+curr[1]-curr[0]+curr[2]);
            }
        }
        return dp[n];
    }

}
