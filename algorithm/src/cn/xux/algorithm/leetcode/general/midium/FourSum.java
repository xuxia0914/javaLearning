package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums==null||nums.length<4) {
            return  result;
        }
        Arrays.sort(nums);
        int n = nums.length;
        for(int i=0;i<n-3;i++) {
            if(i>0&&nums[i]==nums[i-1]) {
                continue;
            }
            for(int j=i+1;j<n-2;j++) {
                if(j>i+1&&nums[j]==nums[j-1]) {
                    continue;
                }
                int left = j+1, right = n-1;
                while(left<right) {
                    int sum = nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target) {
                        List<Integer> tmp = new ArrayList<Integer>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[left]);
                        tmp.add(nums[right]);
                        result.add(tmp);
                        left++;
                        while(nums[left]==nums[left-1]) {
                            left++;
                        }
                        right--;
                        while(nums[right]==nums[right+1]) {
                            right--;
                        }
                    }else if(sum<target) {
                        left++;
                        while(nums[left]==nums[left-1]) {
                            left++;
                        }
                    }else {
                        right--;
                        while(nums[right]==nums[right+1]) {
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }

}
