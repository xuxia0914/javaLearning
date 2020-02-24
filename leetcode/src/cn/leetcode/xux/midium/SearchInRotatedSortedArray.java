package cn.leetcode.xux.midium;

/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
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
