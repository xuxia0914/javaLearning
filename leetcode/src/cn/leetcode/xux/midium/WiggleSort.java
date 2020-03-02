package cn.leetcode.xux.midium;

import java.util.Arrays;
import java.util.List;

/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort {

    public static void solution(Integer[] nums) {
        if(nums==null||nums.length<2) {
            return ;
        }
        for(int i=1;i<nums.length;i++) {
            if((i%2==0&&nums[i]>nums[i-1])||(i%2==1&&nums[i]<nums[i-1])) {
                nums[i] = nums[i]^nums[i-1];
                nums[i-1] = nums[i]^nums[i-1];
                nums[i] = nums[i]^nums[i-1];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{3, 5, 2, 1, 6, 4};
        solution(nums);
        List<Integer> list = Arrays.asList(nums);
        System.out.println(Arrays.asList(nums));
    }

}
