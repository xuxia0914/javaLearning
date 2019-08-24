package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * Given an array nums of n integers where n > 1,
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
    /**
     * 用了除法
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if(nums==null||nums.length==0) {
            return null;
        }
        int pro = 1;
        int countZero = 0;
        for(int i : nums) {
            if(i==0) {
                countZero++;
            }else {
                pro *= i;
            }
        }
        if(countZero>1) {
            Arrays.fill(nums, 0);
        }else if(countZero==1) {
            for(int i=0;i<nums.length;i++) {
                if(nums[i]==0) {
                    nums[i] = pro;
                }else {
                    nums[i] = 0;
                }
            }
        }else {
            for(int i=0;i<nums.length;i++) {
                nums[i] = pro/nums[i];
            }
        }
        return nums;
    }

    public int[] productExceptSelf1(int[] nums) {
        if(nums==null||nums.length==0) {
            return null;
        }
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, 1);
        for(int i=1;i<len;i++) {
            res[i] = res[i-1]*nums[i-1];
        }
        int right = 1;
        for(int i=len-1;i>=0;i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

}
