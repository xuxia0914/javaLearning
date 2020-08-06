package cn.xux.algorithm.leetcode.general.midium;

/**
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 * Example:
 * nums = [1, 2, 3]
 * target = 4
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */
public class CombinationSumIV {

    int res = 0;

    /**TLE*/
    public int combinationSum41(int[] nums, int target) {
        if(target==0) {
            res++;
        }
        for(int i : nums) {
            if(i<=target) {
                combinationSum41(nums, target-i);
            }
        }
        return res;
    }

    public int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i=1;i<=target;i++) {
            for(int j : nums) {
                if(j<=i) {
                    dp[i] += dp[i-j];
                }
            }
        }
        return dp[target];
    }


}
