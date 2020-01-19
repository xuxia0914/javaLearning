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
        //考虑left=right=0的情况
        int low = left==0&&nums[left]>=target?left-1:left;
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
        //考虑left=right=nums.length-1的情况
        int high = left==nums.length-1&&nums[left]<=target?left+1:left;
        if(low+1>high-1) {
            return new int[]{-1, -1};
        }else {
            return new int[]{low+1, high-1};
        }
    }

    public int[] searchRange1(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if(nums==null||nums.length==0) {
            return result;
        }
        int n = nums.length;
        result[0] = searchLeft(nums, target, 0, n-1);
        result[1] = searchRight(nums, target, 0, n-1);
        return result;
    }

    public int searchLeft(int[] nums, int target, int start, int end) {
        if(start>end) {
            return -1;
        }
        int mid = (start+end)/2;
        if(target<nums[start]) {
            return -1;
        }else if(target==nums[start]) {
            return start;
        }else if(target>nums[start]&&target<nums[mid]) {
            return searchLeft(nums, target, start+1, mid-1);
        }else if(target==nums[mid]) {
            return searchLeft(nums, target, start+1, mid);
        }else if(target>nums[mid]&&target<nums[end]) {
            return searchLeft(nums, target, mid+1, end-1);
        }else if(target==nums[end]) {
            return searchLeft(nums, target, mid+1, end);
        }else {
            return -1;
        }
    }

    public int searchRight(int[] nums, int target, int start, int end) {
        if(start>end) {
            return -1;
        }
        int mid = (start+end)/2;
        if(target>nums[end]) {
            return -1;
        }else if(target==nums[end]) {
            return end;
        }else if(target>nums[mid]&&target<nums[end]) {
            return searchRight(nums, target, mid+1, end-1);
        }else if(target==nums[mid]) {
            return searchRight(nums, target, mid, end-1);
        }else if(target>nums[start]&&target<nums[mid]) {
            return searchRight(nums, target, start+1, mid-1);
        }else if(target==nums[start]) {
            return searchRight(nums, target, start, mid-1);
        }else {
            return -1;
        }
    }

}
