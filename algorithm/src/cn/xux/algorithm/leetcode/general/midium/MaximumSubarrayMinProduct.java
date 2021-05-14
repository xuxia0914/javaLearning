package cn.xux.algorithm.leetcode.general.midium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1856. 子数组最小乘积的最大值
 * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
 * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
 * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。
 * 由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
 * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。
 * 题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
 * 子数组 定义为一个数组的 连续 部分。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,2]
 * 输出：14
 * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
 * 2 * (2+3+2) = 2 * 7 = 14 。
 *
 * 示例 2：
 * 输入：nums = [2,3,3,1,2]
 * 输出：18
 * 解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
 * 3 * (3+3) = 3 * 6 = 18 。
 *
 * 示例 3：
 * 输入：nums = [3,1,5,6,4,2]
 * 输出：60
 * 解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
 * 4 * (5+6+4) = 4 * 15 = 60 。
 *
 * 提示：
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 107
 */
public class MaximumSubarrayMinProduct {

    public static void main(String[] args) {
        System.out.println(new MaximumSubarrayMinProduct().maxSumMinProduct(new int[]{1,2,3,2}));
    }

    /**
     * 以当前nums[i]为中心，往左往右找不小于nums[i]最远处l和r
     * 这样sum[l~r]最大
     * nums[i] * sum[l~r]最大
     */
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;

        int[] l = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        deque.offerLast(-1);
        for(int i=0;i<n;i++) {
            while(deque.peekLast()!=-1&&nums[deque.peekLast()]>=nums[i]) {
                deque.pollLast();
            }
            l[i] = deque.peekLast();
            deque.offerLast(i);
        }

        int[] r = new int[n];
        deque = new LinkedList<>();
        deque.offerLast(n);
        for(int i=n-1;i>=0;i--) {
            while(deque.peekLast()!=n&&nums[deque.peekLast()]>=nums[i]) {
                deque.pollLast();
            }
            r[i] = deque.peekLast();
            deque.offerLast(i);
        }

        long[] preSum = new long[n+1];
        for(int i=0;i<n;i++) {
            preSum[i+1] = preSum[i]+nums[i];
        }

        long ans = 0;
        for(int i=0;i<n;i++) {
            ans = Math.max(ans, (preSum[r[i]]-preSum[l[i]+1])*nums[i]);
        }
        return (int)(ans%1000000007);
    }

    // TLE
    public int maxSumMinProduct1(int[] nums) {
        int n = nums.length;
        long[] preSum = new long[n+1];
        for(int i=0;i<n;i++) {
            preSum[i+1] = preSum[i]+nums[i];
        }
        bSearch(nums, preSum, 0, n-1);
        return (int)(ans%1000000007);
    }

    long ans = 0;

    private void bSearch(int[] nums, long[] preSum, int start, int end) {
        if(start>end) {
            return;
        }
        long sum = preSum[end+1]-preSum[start];
        int min = nums[start];
        int minIdx = start;
        for(int i=start+1;i<=end;i++) {
            if(nums[i]<min) {
                min = nums[i];
                minIdx = i;
            }
        }
        ans = Math.max(ans, sum*min);
        bSearch(nums, preSum, start, minIdx-1);
        bSearch(nums, preSum, minIdx+1, end);
    }

}
