package cn.leetcode.xux.easy;

import java.util.Arrays;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 * Example 1:
 * Input: [1,2,3]
 * Output: 6
 * Example 2:
 * Input: [1,2,3,4]
 * Output: 24
 * Note:
 * The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 * Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class MaximumProductOfThreeNumbers {

    /**O(n)*/
    public int maximumProduct1(int[] nums) {
        if(nums==null||nums.length<3) {
            return 0;
        }
        int[] resMax = new int[3];
        resMax[0] = Math.max(Math.max(nums[0], nums[1]), nums[2]);
        resMax[1] = Math.max(Math.max(nums[0]*nums[1], nums[1]*nums[2]), nums[0]*nums[2]);
        resMax[2] = nums[0]*nums[1]*nums[2];
        int[] tmpMax;
        int[] resMin = new int[3];
        resMin[0] = Math.min(Math.min(nums[0], nums[1]), nums[2]);
        resMin[1] = Math.min(Math.min(nums[0]*nums[1], nums[1]*nums[2]), nums[0]*nums[2]);
        resMin[2] = nums[0]*nums[1]*nums[1];
        int[] tmpMin;
        for(int i=3;i<nums.length;i++) {
            tmpMax = resMax.clone();
            tmpMin = resMin.clone();
            resMax[0] = Math.max(tmpMax[0], nums[i]);
            resMax[1] = Math.max(tmpMax[1], Math.max(tmpMax[0]*nums[i], tmpMin[0]*nums[i]));
            resMax[2] = Math.max(tmpMax[2], Math.max(tmpMax[1]*nums[i], tmpMin[1]*nums[i]));
            resMin[0] = Math.min(tmpMin[0], nums[i]);
            resMin[1] = Math.min(tmpMin[1], Math.min(tmpMin[0]*nums[i], tmpMax[0]*nums[i]));
            resMin[2] = Math.min(tmpMin[2], Math.min(tmpMin[1]*nums[i], tmpMax[1]*nums[i]));
        }
        return resMax[2];
    }

    /**O(nlogn)*/
    public int maximumProduct2(int[] nums) {
        if(nums==null||nums.length<3) {
            return 0;
        }
        Arrays.sort(nums);
        int n = nums.length;
        int res = nums[n-1] * nums[n-2] * nums[n-3];
        res = Math.max(res, nums[n-1] * nums[n-2] * nums[0]);
        res = Math.max(res, nums[n-1] * nums[1] * nums[0]);
        res = Math.max(res, nums[2] * nums[1] * nums[0]);
        return res;
    }

}
