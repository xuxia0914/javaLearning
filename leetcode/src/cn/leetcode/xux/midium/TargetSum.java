package cn.leetcode.xux.midium;

/**
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 * 注意:
 * 数组的长度不会超过20，并且数组中的值全为正数。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果为32位整数。
 */
public class TargetSum {

    int res = 0;

    /**
     * 执行用时 :410 ms, 在所有 Java 提交中击败了46.85%的用户
     * 内存消耗 :35.1 MB, 在所有 Java 提交中击败了81.70%的用户
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        if(nums==null||nums.length==0) {
            return 0;
        }
        helper(nums, S, 0, 0);
        return res;
    }

    void helper(int[] nums, int S, int idx, int curr) {
        if(idx==nums.length-1) {
            if(curr+nums[idx]==S) {
                res++;
            }
            if(curr-nums[idx]==S) {
                res++;
            }
            return;
        }
        helper(nums, S, idx+1, curr+nums[idx]);
        helper(nums, S, idx+1, curr-nums[idx]);
    }

}
