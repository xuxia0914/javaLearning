package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 689. 三个无重叠子数组的最大和
 * 给定数组 nums 由正整数组成，找到三个互不重叠的子数组的最大和。
 * 每个子数组的长度为k，我们要使这3*k个项的和最大化。
 * 返回每个区间起始索引的列表（索引从 0 开始）。如果有多个结果，返回字典序最小的一个。
 *
 * 示例:
 * 输入: [1,2,1,2,6,7,5,1], 2
 * 输出: [0, 3, 5]
 * 解释: 子数组 [1, 2], [2, 6], [7, 5] 对应的起始索引为 [0, 3, 5]。
 * 我们也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 *
 * 注意:
 * nums.length的范围在[1, 20000]之间。
 * nums[i]的范围在[1, 65535]之间。
 * k的范围在[1, floor(nums.length / 3)]之间。
 */
public class MaximumSumOf3NonOverlappingSubarrays {

    public static void main(String[] args) {
        MaximumSumOf3NonOverlappingSubarrays ms = new MaximumSumOf3NonOverlappingSubarrays();
        // 1,3,5
        System.out.println(Arrays.toString(ms.maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2)));
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        // sums[i]表示从i开始的连续k个元素的和
        int[] sums = new int[len-k+1];
        int sum = 0;
        for(int i=len-1;i>len-k;i--) {
            sum += nums[i];
        }
        int pre = 0;
        for(int i=len-k;i>=0;i--) {
            sum += nums[i]-pre;
            sums[i] = sum;
            pre = nums[i+k-1];
        }
        // dp1[i]表示从i开始的所有元素中，使长度为k的子数组和最大的起始位置
        int[] dp1 = new int[len-k+1];
        dp1[len-k] = len-k;
        for(int i=len-k-1;i>=0;i--) {
            if(sums[i]>=sums[dp1[i+1]]) {
                dp1[i] = i;
            }else {
                dp1[i] = dp1[i+1];
            }
        }
        // dp1[i][j]表示从i开始的所有元素中，使长度为k的两个子数组和最大的第j个子数组的起始位置
        int[][] dp2 = new int[len-2*k+1][2];
        dp2[len-2*k][0] = len-2*k;
        dp2[len-2*k][1] = len-k;
        for(int i=len-2*k-1;i>=0;i--) {
            int curr = sums[i]+sums[dp1[i+k]];
            if(curr>=sums[dp2[i+1][0]]+sums[dp2[i+1][1]]) {
                dp2[i][0] = i;
                dp2[i][1] = dp1[i+k];
            }else {
                dp2[i] = dp2[i+1];
            }
        }

        //ans[i] 使长度为k的3个子数组和最大的第i个子数组的起始位置(从后向前迭代)
        int[] ans = new int[3];
        ans[0] = len-3*k;
        ans[1] = len-2*k;
        ans[2] = len-k;
        int preSum = sums[ans[0]]+sums[ans[1]]+sums[ans[2]];

        for(int i=len-3*k-1;i>=0;i--) {
            int curr = sums[i]+sums[dp2[i+k][0]]+sums[dp2[i+k][1]];
            if(curr>=preSum) {
                ans[0] = i;
                ans[1] = dp2[i+k][0];
                ans[2] = dp2[i+k][1];
                preSum = curr;
            }
        }

        return ans;
    }

}
