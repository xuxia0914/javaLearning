package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;    
        for(int i=0;i<n-2;i++) if(i==0||nums[i]!=nums[i-1]) {
            int left = i+1;
            int right = n-1;
            while(left<right) {
                while(left<right&&left>i+1&&nums[left]==nums[left-1]) {
                    left++;
                }
                while(left<right&&right<n-1&&nums[right]==nums[right+1]) {
                    right--;
                }
                if(left<right) {
                    int sum = nums[i]+nums[left]+nums[right];
                    if(sum==0) {
                        ans.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                    }else if(sum<0) {
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        System.out.println(ts.threeSum(new int[]{-1, 0, 1, 2, -1, -1, -1, 2, 2, 2}));
    }

}
