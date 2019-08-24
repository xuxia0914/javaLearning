package cn.leetcode.xux.midium;

/**
 *Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchInRotatedSortedArray {

    //递归
    public int search1(int[] nums, int target) {
        return helper(nums, target, 0, nums.length-1);
    }

    int helper(int[] nums, int target, int start, int end) {
        if(start>end) {
            return -1;
        }
        if(start==end) {
            if(nums[start]==target) {
                return start;
            }else {
                return -1;
            }
        }
        int mid = (start+end)/2;
        if(nums[start]==target) {
            return start;
        }
        if(nums[end]==target) {
            return end;
        }
        if(nums[mid]==target) {
            return mid;
        }
        if(nums[start]<nums[mid]) {
            if(target>nums[start]&&target<nums[mid]) {
                return helper(nums, target, start+1, mid-1);
            }else {
                return helper(nums, target, mid+1, end-1);
            }
        }else {
            if(target<nums[end]&&target>nums[mid]) {
                return helper(nums, target, mid+1, end-1);
            }else {
                return helper(nums, target, start+1, mid-1);
            }
        }
    }

    //迭代
    public int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        while(left<=right) {
            int mid = (left+right)/2;
            if(nums[left]==target) {
                return left;
            }
            if(nums[right]==target) {
                return right;
            }
            if(nums[mid]==target) {
                return mid;
            }
            if(nums[left]<nums[mid]) {
                if(target>nums[left]&&target<nums[mid]) {
                    left++;
                    right = mid-1;
                }else {
                    left = mid+1;
                    right--;
                }
            }else {
                if(target>nums[mid]&&target<nums[right]) {
                    left = mid+1;
                    right--;
                }else {
                    left++;
                    right = mid-1;
                }
            }
        }
        return -1;
    }

}
