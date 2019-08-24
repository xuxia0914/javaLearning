package cn.leetcode.xux.midium;

import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * Example 1:
 * Input: nums = [1, 5, 1, 1, 6, 4]
 * Output: One possible answer is [1, 4, 1, 5, 1, 6].
 * Example 2:
 * Input: nums = [1, 3, 2, 2, 3, 1]
 * Output: One possible answer is [2, 3, 1, 3, 1, 2].
 * Note:
 * You may assume all input has valid answer.
 * Follow Up:
 * Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSortII {

    /**time O(nlogn) mem O(n)*/
    public static void wiggleSort(Integer[] nums) {
        if(nums==null||nums.length<2) {
            return ;
        }
        Integer[] tmp = nums.clone();
        Arrays.sort(tmp);
        int left=(nums.length-1)/2,right=nums.length-1;
        for(int i=0;i<nums.length;i++) {
            nums[i] = i%2==0?tmp[left--]:tmp[right--];
        }
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{1, 5, 1, 1, 6, 4, 7};
        wiggleSort(nums);
        System.out.println(Arrays.asList(nums));
    }

}
