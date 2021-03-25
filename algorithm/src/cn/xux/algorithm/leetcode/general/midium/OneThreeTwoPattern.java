package cn.xux.algorithm.leetcode.general.midium;

import java.util.Stack;

/**
 * 456. 132模式
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。
 * 设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 * 注意：n 的值小于15000。
 *
 * 示例1:
 * 输入: [1, 2, 3, 4]
 * 输出: False
 * 解释: 序列中不存在132模式的子序列。
 *
 * 示例 2:
 * 输入: [3, 1, 4, 2]
 * 输出: True
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 *
 * 示例 3:
 * 输入: [-1, 3, 2, 0]
 * 输出: True
 * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 */
public class OneThreeTwoPattern {

    public boolean find132pattern(int[] nums) {
        if(nums==null||nums.length<3) {
            return false;
        }
        int len = nums.length;
        int[] mins = new int[len];
        mins[0] = nums[0];
        for(int i=1;i<len;i++) {
            mins[i] = Math.min(mins[i-1], nums[i]);
        }
        Stack<Integer> stack = new Stack<>();
        for(int i=len-1;i>0;i--) {
            if(nums[i]>mins[i]) {
                while(!stack.isEmpty()&&stack.peek()<=mins[i]) {
                    stack.pop();
                }
                if(!stack.isEmpty()&&stack.peek()<nums[i]) {
                    return true;
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

}
