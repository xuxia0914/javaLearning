package cn.xux.algorithm.leetcode.general.midium;

/**
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。
 * 如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 *
 *
 * 进阶：
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
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
