package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 324. 摆动排序 II
 * 给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 *
 * 示例 1:
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 *
 * 示例 2:
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 *
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 *
 * 进阶:
 * 你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class WiggleSortII {

    //时间 O(n)
    public void wiggleSort(int[] nums) {
        if(nums==null||nums.length<2) {
            return;
        }
        int[] newNums = nums.clone();
        int len = newNums.length;
        //快速选择
        int mid = quickSelect(newNums, 0, len-1, (len-1)/2);
        //三色排序
        int left = 0;
        int right = len-1;
        int curr = 0;
        int tmp;
        while(curr<=right) {
            if(newNums[curr]>mid) {
                tmp = newNums[right];
                newNums[right--] = newNums[curr];
                newNums[curr] = tmp;
            }else if(newNums[curr]<mid) {
                tmp = newNums[left];
                newNums[left++] = newNums[curr];
                newNums[curr++] = tmp;
            }else {
                curr++;
            }
        }
        //摆动排序
        left=(len-1)/2;
        right=len-1;
        for(int i=0;i<len;i++) {
            nums[i] = i%2==0?newNums[left--]:newNums[right--];
        }
    }

    public int quickSelect(int[] nums, int start, int end, int k) {
        if(start==end) {
            return nums[start];
        }
        int left = start;
        int right = end;
        int key = nums[left];
        while(left<right) {
            while(left<right&&nums[right]>=key) {
                right--;
            }
            while(left<right&&nums[left]<=key) {
                left++;
            }
            if(left<right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        nums[start] = nums[right];
        nums[right]= key;
        if(right==k) {
           return nums[right];
        }else if(right<k) {
            return quickSelect(nums, right+1, end, k);
        }else {
            return quickSelect(nums, start, right-1, k);
        }
    }

    /**time O(nlogn)*/
    public void wiggleSort1(int[] nums) {
        if(nums==null||nums.length<2) {
            return ;
        }
        int[] newNums = nums.clone();
        int len = newNums.length;
        Arrays.sort(newNums);
        //摆动排序
        int left=(len-1)/2;
        int right=len-1;
        for(int i=0;i<len;i++) {
            nums[i] = i%2==0?newNums[left--]:newNums[right--];
        }
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{1, 5, 1, 1, 6, 4, 7};
        //int[] nums = new int[]{4,5,5,6};
        //int[] nums = new int[]{5,3,1,2,6,7,8,5,5};
        int[] nums = new int[]{1,5,1,1,6,4};
        new WiggleSortII().wiggleSort(nums);
        for(int num : nums) {
            System.out.print(num+" ");
        }
    }

}
