package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * 983. 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行
 * 在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。
 * 每一项是一个从 1 到 365 的整数。
 *
 * 火车票有三种不同的销售方式：
 * 一张为期一天的通行证售价为 costs[0] 美元；
 * 一张为期七天的通行证售价为 costs[1] 美元；
 * 一张为期三十天的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 *
 * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 *
 * 示例 1：
 * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
 * 输出：11
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
 * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
 * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
 * 你总共花了 $11，并完成了你计划的每一天旅行。
 *
 * 示例 2：
 * 输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * 输出：17
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
 * 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。
 * 你总共花了 $17，并完成了你计划的每一天旅行。
 *
 * 提示：
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days 按顺序严格递增
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
public class MinimumCostForTickets {

    /**
     * 时间复杂度O(n),空间复杂度O(n)（其中max(days[i])）
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets1(int[] days, int[] costs) {
        int n = days.length;
        int maxDay = days[n-1];
        int[] dp = new int[maxDay+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int idx = 0;
        int pre = 0;
        for(int i=0;i<=maxDay;i++) {
            if(i<days[idx]) {
                dp[i] = pre;
            }
            if(i==days[idx]) {
                if(i-1>=0) {
                    dp[i] = Math.min(dp[i], dp[i-1]+costs[0]);
                }else {
                    dp[i] = Math.min(dp[i], costs[0]);
                }
                if(i-7>=0) {
                    dp[i] = Math.min(dp[i], dp[i-7]+costs[1]);
                }else {
                    dp[i] = Math.min(dp[i], costs[1]);
                }
                if(i-30>=0) {
                    dp[i] = Math.min(dp[i], dp[i-30]+costs[2]);
                }else {
                    dp[i] = Math.min(dp[i], costs[2]);
                }
                pre = dp[i];
                idx++;
            }
        }
        return dp[maxDay];
    }

    /**
     * 时间复杂度O(nlogn),空间复杂度O(n)（其中n=days.length）
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for(int i=0;i<n;i++) {
            if(days[i]-1<days[0]) {
                dp[i] = Math.min(dp[i], costs[0]);
            }else {
                dp[i] = Math.min(dp[i], costs[0]+dp[search(days, i-1, days[i]-1)]);
            }
            if(days[i]-7<days[0]) {
                dp[i] = Math.min(dp[i], costs[1]);
            }else {
                dp[i] = Math.min(dp[i], costs[1]+dp[search(days, i-1, days[i]-7)]);
            }
            if(days[i]-30<days[0]) {
                dp[i] = Math.min(dp[i], costs[2]);
            }else {
                dp[i] = Math.min(dp[i], costs[2]+dp[search(days, i-1, days[i]-30)]);
            }
        }
        return dp[n-1];
    }

    //二分查找days在0到end的索引中，第一个值小于或等于target的索引
    public int search(int[] days, int end, int target) {
        int left = 0;
        int right = end;
        while(left<right) {
            int mid = (left+right+1)/2;
            if(days[mid]>target) {
                right = mid-1;
            }else {
                left = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumCostForTickets().mincostTickets1(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}));
    }

}
