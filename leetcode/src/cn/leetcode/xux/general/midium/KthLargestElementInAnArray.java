package cn.leetcode.xux.general.midium;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class KthLargestElementInAnArray {

    //nlogk
    public int findKthLargest(int[] nums, int k) {
        if(nums==null||nums.length==0||k>nums.length||k<1) {
            return -1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int num : nums) {
            queue.offer(num);
            if(queue.size()>k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    //n
    public int findKthLargest1(int[] nums, int k) {
        if(nums==null||nums.length==0||k>nums.length||k<1) {
            return -1;
        }
        return helper(nums, 0, nums.length-1, k);
    }

    public int helper(int[] nums, int start, int end, int k) {
        if(start>end) {
            return -1;
        }
        int left = start;
        int right = end;
        int key = nums[start];
        int tmp;
        while(left<right) {
            while(left<right&&nums[right]>=key) {
                right--;
            }
            while(left<right&&nums[left]<=key) {
                left++;
            }
            if(left<right) {
                tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }
        nums[start] = nums[right];
        nums[right] = key;
        if(right==nums.length-k) {
            return nums[right];
        }else if(right>nums.length-k) {
            return helper(nums, start, right-1, k);
        }else {
            return helper(nums, right+1, end, k);
        }
    }

    public static void main(String[] args) {
        System.out.println(new KthLargestElementInAnArray().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(new KthLargestElementInAnArray().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

}
