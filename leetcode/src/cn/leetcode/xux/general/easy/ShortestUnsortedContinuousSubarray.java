package cn.leetcode.xux.general.easy;

/**
 * 581. 最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
public class ShortestUnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        int m = nums[0];
        int n = nums[nums.length-1];
        int left = 0, right = -1;
        for(int i=1;i<nums.length;i++) {
            m = Math.max(m, nums[i]);
            if(m!=nums[i]) {
                right = i;
            }
            n = Math.min(n, nums[nums.length-1-i]);
            if(n!=nums[nums.length-1-i]) {
                left = nums.length-1-i;
            }
        }
        return right-left+1;
    }

}
