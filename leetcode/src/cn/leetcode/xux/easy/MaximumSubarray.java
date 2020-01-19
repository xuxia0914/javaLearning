package cn.leetcode.xux.easy;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int curr = nums[0];
        int max = nums[0];
        for(int i=1;i<nums.length;i++) {
            curr = Math.max(curr+nums[i], nums[i]);
            max = Math.max(max, curr);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4,7}));
    }

}
