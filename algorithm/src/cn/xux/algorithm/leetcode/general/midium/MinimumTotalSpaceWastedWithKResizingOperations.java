package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1959. K 次调整数组大小浪费的最小总空间
 * 你正在设计一个动态数组。给你一个下标从 0 开始的整数数组 nums ，
 * 其中 nums[i] 是 i 时刻数组中的元素数目。
 * 除此以外，你还有一个整数 k ，
 * 表示你可以 调整 数组大小的 最多 次数（每次都可以调整成 任意 大小）。
 *
 * t 时刻数组的大小 sizet 必须大于等于 nums[t] ，
 * 因为数组需要有足够的空间容纳所有元素。
 * t 时刻 浪费的空间 为 sizet - nums[t] ，
 * 总 浪费空间为满足 0 <= t < nums.length 的每一个时刻 t 浪费的空间 之和 。
 *
 * 在调整数组大小不超过 k 次的前提下，请你返回 最小总浪费空间 。
 *
 * 注意：数组最开始时可以为 任意大小 ，且 不计入 调整大小的操作次数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [10,20], k = 0
 * 输出：10
 * 解释：size = [20,20].
 * 我们可以让数组初始大小为 20 。
 * 总浪费空间为 (20 - 10) + (20 - 20) = 10 。
 * 示例 2：
 *
 * 输入：nums = [10,20,30], k = 1
 * 输出：10
 * 解释：size = [20,20,30].
 * 我们可以让数组初始大小为 20 ，然后时刻 2 调整大小为 30 。
 * 总浪费空间为 (20 - 10) + (20 - 20) + (30 - 30) = 10 。
 * 示例 3：
 *
 * 输入：nums = [10,20,15,30,20], k = 2
 * 输出：15
 * 解释：size = [10,20,20,30,30].
 * 我们可以让数组初始大小为 10 ，时刻 1 调整大小为 20 ，时刻 3 调整大小为 30 。
 * 总浪费空间为 (10 - 10) + (20 - 20) + (20 - 15) + (30 - 30) + (30 - 20) = 15 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 106
 * 0 <= k <= nums.length - 1
 */
public class MinimumTotalSpaceWastedWithKResizingOperations {

    public static void main(String[] args) {
        System.out.println(new MinimumTotalSpaceWastedWithKResizingOperations()
                .minSpaceWastedKResizing(new int[]{10,20}, 0));
    }

    // 题目描述等价于：
    // 给定数组 nums 以及整数 k，需要把数组完整地分成 k+1 段连续的子数组，
    // 每一段的权值是「这一段的最大值乘以这一段的长度再减去这一段的元素和」。
    // 需要最小化总权值。
    public int minSpaceWastedKResizing(int[] nums, int k) {
        int n = nums.length;
        if(k>=n-1) {
            return 0;
        }
        // dp[i][j] 表示nums[0~i]分成j段可以达到的最小总浪费空间
        // dp[i][j] = min(dp[i0][j-1]+g[i0+1][i]) (i0=0~i-1)
        // g[i0+1][i]表示从i0+1~i这一段的最大值max*(i-i0)-sum(i0+1~i)
        // 所以最终结果为dp[n-1][K+1]
        int[][] dp = new int[n][k+2];
        // 先求出g
        int[] preSum = new int[n+1];
        int[][] max = new int[n][n];
        for(int i=0;i<n;i++) {
            preSum[i+1] = preSum[i]+nums[i];
            max[i][i] = nums[i];
            for(int j=i+1;j<n;j++) {
                max[i][j] = Math.max(max[i][j-1], nums[j]);
            }
        }
        int[][] g = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                g[i][j] = max[i][j]*(j-i+1)-preSum[j+1]+preSum[i];
            }
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<k+2;j++) {
                if(i+1<=j) {
                    continue;
                }
                dp[i][j] = g[0][i];
                if(j>1) {
                    for(int ii=0;ii<i;ii++) {
                        dp[i][j] = Math.min(dp[i][j], dp[ii][j-1]+g[ii+1][i]);
                    }
                }
            }
        }
        return dp[n-1][k+1];
    }

}
