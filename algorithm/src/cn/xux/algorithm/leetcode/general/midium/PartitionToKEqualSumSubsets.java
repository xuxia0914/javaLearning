package cn.xux.algorithm.leetcode.general.midium;

/**
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Note:
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums==null||nums.length<k||k<1) {
            return false;
        }
        int sum = 0;
        int max = 0;
        for(int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if(sum%k!=0) {
            return false;
        }
        int target = sum/k;
        if(max>target) {
            return false;
        }
        return helper(nums, 0, target, target, nums.length);
    }

    public boolean helper(int[] nums, int start, int target, int leftSum, int leftNum) {
        if(leftNum==0) {
            return true;
        }
        for(int i=start;i<nums.length;i++) {
            int num = nums[i];
            if(num==0) {
                continue;
            }
            if(num<=leftSum) {
                nums[i] = 0;
                if(helper(nums, num==leftSum?0:i+1, target, num==leftSum?target:leftSum-num, leftNum-1)){
                    return true;
                }
                nums[i] = num;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets p = new PartitionToKEqualSumSubsets();
        System.out.println(p.canPartitionKSubsets(new int[]{129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22}, 3));
    }

}
