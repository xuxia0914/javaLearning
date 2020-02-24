package cn.leetcode.xux.easy;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
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
