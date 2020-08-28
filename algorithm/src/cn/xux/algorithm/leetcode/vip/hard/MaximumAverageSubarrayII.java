package cn.xux.algorithm.leetcode.vip.hard;

/**
 * 644. 最大平均子段和 II
 * 给定一个包含 n 个整数的数组，找到最大平均值的连续子序列，且长度大于等于 k。并输出这个最大平均值。
 *
 * 样例 1:
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释:
 * 当长度为 5 的时候，最大平均值是 10.8，
 * 当长度为 6 的时候，最大平均值是 9.16667。
 * 所以返回值是 12.75。
 *
 * 注释 :
 * 1 <= k <= n <= 10,000。
 * 数组中的元素范围是 [-10,000, 10,000]。
 * 答案的计算误差小于 10-5 。
 */
public class MaximumAverageSubarrayII {

    public double findMaxAverage(int[] nums, int k) {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        // 寻找最值
        for (int n: nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
        }

        while (max-min > 0.00001) {
            double mid = (max + min) / 2.0;
            if (check(nums, mid, k))
                min = mid;
            else
                max = mid;
        }
        return min;
    }


    // 判断这个区间里面，是否有一段大于等于K的长度的最长序列，满足要求，就是最大的累计和，减去最小的累计和
    public boolean check(int[] nums, double mid, int k) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++)
            sum += nums[i] - mid;
        if (sum >= 0)
            return true;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid;
            prev += nums[i - k] - mid;
            min_sum = Math.min(prev, min_sum);
            if (sum >= min_sum)
                return true;
        }
        return false;
    }

}
