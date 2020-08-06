package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0]+nums[1]+nums[2];
        int n = nums.length;
        for(int i=0;i<n-2;i++) {
            int left = i+1;
            int right = n-1;
            while(left<right) {
                int sum = nums[i]+nums[left]+nums[right];
                if(sum==target) {
                    return target;
                }
                ans = (Math.abs(sum-target)<Math.abs(ans-target))?sum:ans;
                if(sum<target) {
                    left++;
                    while(left<right&&nums[left]==nums[left-1]) {
                        left++;
                    }
                }else {
                    right--;
                    while(left<right&&nums[right]==nums[right+1]) {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

}
