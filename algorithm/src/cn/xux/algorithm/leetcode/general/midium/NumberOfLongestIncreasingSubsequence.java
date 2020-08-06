package cn.xux.algorithm.leetcode.general.midium;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 *
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 */
public class NumberOfLongestIncreasingSubsequence {

    public static void main(String[] args) {
//        System.out.println(new NumberOfLongestIncreasingSubsequence().findNumberOfLIS(new int[]{1,3,5,4,7}));
//        System.out.println(new NumberOfLongestIncreasingSubsequence().findNumberOfLIS(new int[]{2,2,2,2,2,2}));
        System.out.println(new NumberOfLongestIncreasingSubsequence().findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
    }

    public int findNumberOfLIS(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int m = nums.length;
        int[][] dp = new int[m][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int res = 1;
        int maxLen = 1;
        for(int i=1;i<m;i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            for(int j=0;j<i;j++) {
                if(nums[i]>nums[j]) {
                    if(dp[j][0]+1==dp[i][0]) {
                        dp[i][1] += dp[j][1];
                    }else if(dp[j][0]+1>dp[i][0]) {
                        dp[i][0] = dp[j][0]+1;
                        dp[i][1] = dp[j][1];
                    }
                }
            }
            if(dp[i][0]==maxLen) {
                res += dp[i][1];
            }else if(dp[i][0]>maxLen) {
                maxLen = dp[i][0];
                res = dp[i][1];
            }
        }
        return res;
    }

}
