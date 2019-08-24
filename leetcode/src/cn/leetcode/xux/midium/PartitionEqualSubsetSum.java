package cn.leetcode.xux.midium;

/**
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * Example 1:
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if(nums==null||nums.length<2) {
            return false;
        }
        int sum = 0;
        for(int i : nums) {
            sum += i;
        }
        if(sum%2==1) {
            return false;
        }
        int target = sum/2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int num : nums) {
            for(int i=target-num;i>=0;i--) {
                if(dp[i]) {
                    if(i+num<target) {
                        dp[i+num] = true;
                    }else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum pess = new PartitionEqualSubsetSum();
        System.out.println(pess.canPartition(new int[]{1,2,5}));
    }

}
