package cn.leetcode.xux.easy;

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 *
 * 示例 1:
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 * 注意:
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 */
public class MaximumAverageSubarrayI {

    public static void main(String[] args) {
        System.out.println(new MaximumAverageSubarrayI().findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
    }

    public double findMaxAverage(int[] nums, int k) {
        for(int i=1;i<=k-1;i++) {
            nums[i] += nums[i-1];
        }
        int result = nums[k-1];
        for(int i=k;i<nums.length;i++) {
            nums[i] += nums[i-1];
            result = Math.max(result, nums[i]-nums[i-k]);
        }
        return (double)result/k;
    }

}
