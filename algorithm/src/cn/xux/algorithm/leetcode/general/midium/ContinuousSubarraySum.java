package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. 连续的子数组和
 * 给定一个包含非负数的数组和一个目标整数 k，
 * 编写一个函数来判断该数组是否含有连续的子数组，
 * 其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 *
 * 示例 1:
 * 输入: [23,2,4,6,7], k = 6
 * 输出: True
 * 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
 *
 * 示例 2:
 * 输入: [23,2,6,4,7], k = 6
 * 输出: True
 * 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 *
 * 说明:
 * 数组的长度不会超过10,000。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 */
public class ContinuousSubarraySum {

    /**
     * 执行用时 :65 ms, 在所有 Java 提交中击败了19.76%的用户
     * 内存消耗 :48.7 MB, 在所有 Java 提交中击败了39.64%的用户
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums==null||nums.length==0) {
            return false;
        }
        for(int i=0;i<nums.length;i++) {
            int sum = nums[i];
            for(int j=i+1;j<nums.length;j++) {
                sum += nums[j];
                if(k==0&&sum==0) {
                    return true;
                }
                if(k!=0&&sum%k==0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 执行用时 :7 ms, 在所有 Java 提交中击败了89.72%的用户
     * 内存消耗 :49 MB, 在所有 Java 提交中击败了37.84%的用户
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum1(int[] nums, int k) {
        if(nums==null||nums.length==0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum += nums[i];
            int target = k==0?sum:sum%k;
            if(map.containsKey(target)&&map.get(target)+2<=i) {
                return true;
            }
            if(!map.containsKey(target)) {
                map.put(target, i);
            }
        }
        return false;
    }

}
