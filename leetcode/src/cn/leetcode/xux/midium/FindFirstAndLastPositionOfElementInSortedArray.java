package cn.leetcode.xux.midium;

/**
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if(nums==null||nums.length==0) {
            return new int[]{-1,-1};
        }
        int left = 0, right = nums.length-1;
        int mid;
        while(left<right) {
            mid = (left+right+1)/2;
            if(nums[mid]>=target) {
                right = mid-1;
            }else {
                left = mid;
            }
        }
        int low = left==0&&nums[left]>=target?left-1:left;  //考虑left=right=0的情况
        left = 0;
        right = nums.length-1;
        while(left<right) {
            mid = (left+right)/2;
            if(nums[mid]<=target) {
                left = mid+1;
            }else {
                right = mid;
            }
        }
        int high = left==nums.length-1&&nums[left]<=target?left+1:left; //考虑left=right=nums.length-1的情况
        if(low+1>high-1) {
            return new int[]{-1, -1};
        }else {
            return new int[]{low+1, high-1};
        }
    }

}
