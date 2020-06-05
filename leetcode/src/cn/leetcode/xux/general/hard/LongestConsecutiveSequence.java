package cn.leetcode.xux.general.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        int result = 1;
        for(int num : nums) {
            if(!set.contains(num-1)) {
                int curr = num;
                while(set.contains(curr+1)) {
                    curr++;
                }
                result = Math.max(result, curr-num+1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{100,1,200,4,2,3}));
    }

}
