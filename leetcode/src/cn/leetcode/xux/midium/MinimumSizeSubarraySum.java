package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例:
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 * 进阶:
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */
public class MinimumSizeSubarraySum {

    /*O(n)*/
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = n+1;
        while(right<n&&left<=right) {
            while(sum<s&&right<n) {
                sum += nums[right++];
            }
            while(sum>=s&&left<=right) {
                res = Math.min(res, right-left);
                sum -= nums[left++];
            }
        }
        return res==n+1?0:res;
    }

    /*O(nlogn)*/
    public int minSubArrayLen1(int s, int[] nums) {
        int n = nums.length;
        int[] sums = new int[n];
        sums[0] = nums[0];
        int res = n+1;
        for(int i=1;i<n;i++) {
            sums[i] = sums[i-1]+nums[i];
        }
        for(int i=0;i<n;i++) {
            if(sums[i]>=s) {
                int idx = Arrays.binarySearch(sums, 0, i-1, sums[i]-s);
                if(idx<0) {
                    idx = -idx-2;
                }
                res = Math.min(res, i-idx);
            }
        }
        return res==n+1?0:res;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen1(15, new int[]{1,2,3,4,5}));
    }

}
