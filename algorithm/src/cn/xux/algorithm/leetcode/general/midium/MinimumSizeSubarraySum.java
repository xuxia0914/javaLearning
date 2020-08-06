package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 * 如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例:
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 * 进阶:
 * 如果你已经完成了O(n) 时间复杂复杂度的解法, 请尝试 O(n log n) 时间度的解法。
 */
public class MinimumSizeSubarraySum {

    /*O(n)*/
    public int minSubArrayLen(int s, int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int n = nums.length;
        int ans = n+1;
        int left = 0;
        int sum = nums[0];
        if(sum>s) {
            return 1;
        }
        for(int right=1;right<n;right++) {
            sum += nums[right];
            while(sum>=s) {
                ans = Math.min(ans, right-left+1);
                sum -= nums[left++];
            }
        }
        return ans==n+1?0:ans;
    }

    /*O(nlogn)*/
    public int minSubArrayLen1(int s, int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int n = nums.length;
        int res = n+1;
        int sum = 0;
        for(int i=0;i<n;i++) {
            sum += nums[i];
            nums[i] = sum;
            if(sum>=s) {
                int idx = Arrays.binarySearch(nums, 0, i, sum-s);
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
