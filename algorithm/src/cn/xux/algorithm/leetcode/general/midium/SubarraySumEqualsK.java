package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int sum = 0;
        int result = 0;
        Map<Integer, Integer> mem = new HashMap<>();
        mem.put(0, 1);
        for(int num : nums) {
            sum += num;
            result += mem.getOrDefault(sum-k, 0);
            mem.put(sum, mem.getOrDefault(sum, 0)+1);
        }
        return result;
    }

}
