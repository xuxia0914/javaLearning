package cn.leetcode.xux.easy;

import java.util.Arrays;

/**
 * 求下一个更大的数组序列，如果已经最大，则改成最小的
 * 要求原地修改
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if(nums==null||nums.length<2) {
            return ;
        }
        int len = nums.length;
        boolean flag = true;
        for(int i=len-1;i>0;i--) {
            if(nums[i-1]<nums[i]) {
                for(int j=len-1;j>=0;j--) {
                    if(nums[j]>nums[i-1]) {
                        nums[j] = nums[j]^nums[i-1];
                        nums[i-1] = nums[j]^nums[i-1];
                        nums[j] = nums[j]^nums[i-1];
                        reverse(nums, i, len-1);
                        flag = false;
                        break;
                    }
                }
            }
            if(!flag) {
                break;
            }
        }
        if(flag) {
            reverse(nums, 0, len-1);
        }
    }

    public void reverse(int[] nums, int start, int end) {
        while(start<end) {
            nums[start] = nums[start]^nums[end];
            nums[end] = nums[start]^nums[end];
            nums[start] = nums[start]^nums[end];
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        /*System.out.println(Arrays.toString(solution(new int[]{})));
        System.out.println(Arrays.toString(solution(new int[]{1})));
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(solution(new int[]{3, 2, 1})));
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 5})));*/
        int[] nums = new int[]{1, 3, 2};
        new NextPermutation().nextPermutation(nums);
        for(int i : nums) {
            System.out.print(i + ", ");
        }
    }

}
