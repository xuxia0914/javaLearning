package cn.leetcode.xux.general.hard;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，
 * 你可以获得 nums[left] * nums[i] * nums[right] 个硬币。
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。
 * 注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 *
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {

    public static void main(String[] args) {
        System.out.println(new BurstBalloons().maxCoins(new int[]{3,1,5,8}));
    }

    public int maxCoins(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int n = nums.length+2;
        int[] newNums = new int[n];
        newNums[0] = 1;
        newNums[n-1] = 1;
        System.arraycopy(nums, 0, newNums, 1, n - 1 - 1);
        int[][] dp = new int[n][n];
        for (int left = n-2; left > -1; left--) {
            for (int right = left+2; right < n; right++) {
                for (int i = left + 1; i < right; ++i) {
                    dp[left][right] = Math.max(dp[left][right],
                            newNums[left]*newNums[i]*newNums[right]+dp[left][i]+dp[i][right]);
                }
            }
        }
        return dp[0][n-1];
    }

}
