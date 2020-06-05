package cn.leetcode.xux.general.easy;

import java.util.*;

/**
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class ContainsDuplicateII {

    /**
     * 执行用时 :25 ms, 在所有 Java 提交中击败了60.10%的用户
     * 内存消耗 :51.8 MB, 在所有 Java 提交中击败了14.64%的用户
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 执行用时 :1028 ms, 在所有 Java 提交中击败了5.02%的用户
     * 内存消耗 :43.5 MB, 在所有 Java 提交中击败了73.03%的用户
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        for(int i=0;i<nums.length-1;i++) {
            for(int j=i+1;j<=Math.min(nums.length-1,i+k);j++) {
                if(nums[i]==nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

}
