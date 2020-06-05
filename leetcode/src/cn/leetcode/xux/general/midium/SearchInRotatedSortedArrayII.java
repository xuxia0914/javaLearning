package cn.leetcode.xux.general.midium;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * You are given a target value to search. If found in the array return true, otherwise return false.
 * Example 1:
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * Follow up:
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */
public class SearchInRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        if(nums==null||nums.length==0) {
            return false;
        }
        int left = 0;
        int right = nums.length-1;
        int mid;
        while(left<=right) {
            mid = (left+right)/2;
            if(nums[left]==target||nums[right]==target||nums[mid]==target) {
                return true;
            }
            if(nums[left]<nums[mid]) {
                if(target>nums[left]&&target<nums[mid]) {
                    left++;
                    right = mid-1;
                }else {
                    left = mid+1;
                    right--;
                }
            }else if(nums[left]>nums[mid]) {
                if(target>nums[mid]&&target<nums[right]) {
                    left = mid+1;
                    right--;
                }else {
                    left++;
                    right = mid-1;
                }
            }else {
                left++;
                right--;
            }
        }
        return false;
    }

}
