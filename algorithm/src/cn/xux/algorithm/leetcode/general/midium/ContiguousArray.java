package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 525. 连续数组
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 *
 * 示例 1:
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 *
 * 示例 2:
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 * 注意: 给定的二进制数组的长度不会超过50000。
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        int len = nums.length;
        int[] mem = new int[2*len+1];
        Arrays.fill(mem, -1);
        int result = 0;
        int flag = len;
        for(int i=0;i<nums.length;i++) {
            flag += nums[i]==1?1:-1;
            if(flag==len) {
                result = Math.max(result, i+1);
            }else if(mem[flag]!=-1) {
                result = Math.max(result, i-mem[flag]);
            }else {
                mem[flag] = i;
            }
        }
        return result;
    }

}
