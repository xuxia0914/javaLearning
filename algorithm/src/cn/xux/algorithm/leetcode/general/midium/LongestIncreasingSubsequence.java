package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */
public class LongestIncreasingSubsequence {

    /**O(nlogn)*/
    public int lengthOfLIS(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int len = nums.length;
        int index = 0;
        for(int i=1;i<len;i++) {
            if(nums[i]>nums[index]) {
                nums[++index] = nums[i];
            }else {
                int tmp = Arrays.binarySearch(nums, 0, index, nums[i]);
                if(tmp<0) {
                    tmp = -tmp-1;
                }
                nums[tmp] = nums[i];
            }
        }
        return index+1;
    }

    /**O(n2)*/
    public static int lengthOfLIS1(int[] array) {
        if(array==null||array.length==0) {
            return 0;
        }
        int[] dp = new int[array.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for(int i=1;i<array.length;i++) {
            for(int j=i-1;j>=0;j--) {
                if(array[i]>array[j]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }

}
