package cn.leetcode.xux.easy;

import java.util.Arrays;

/**
 * 求下一个更大的数组序列，如果已经最大，则改成最小的
 * 要求原地修改
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if(nums==null||nums.length<2) {
            return;
        }
        int n = nums.length;
        int left = n;
        for(int i=n-1;i>0;i--) {
            if(nums[i]>nums[i-1]) {
                left = i-1;
                break;
            }
        }
        if(left==n) {
            reverse(nums, 0, n-1);
            return;
        }
        int right = left+1;
        for(int j=left+1;j<n;j++) {
            if(nums[j]>nums[left]&&nums[j]<=nums[right]) {
                right = j;
            }
        }
        swap(nums, left, right);
        reverse(nums, left+1, n-1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int start, int end) {
        while(start<end) {
            swap(nums, start++, end--);
        }
    }

    public static void main(String[] args) {
        /*System.out.println(Arrays.toString(solution(new int[]{})));
        System.out.println(Arrays.toString(solution(new int[]{1})));
        System.out.println(Arrays.toString(solution(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(solution(new int[]{3, 2, 1})));
        System.out.println(Arrays.toString(solution(new int[]{1, 1, 5})));*/
        int[] nums = new int[]{2, 3, 1, 3, 3};
        new NextPermutation().nextPermutation(nums);
        for(int i : nums) {
            System.out.print(i + ", ");
        }
    }

}
