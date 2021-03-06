package cn.xux.algorithm.leetcode.general.easy;

/**
 * 485. 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 *
 * 注意：
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 */
public class MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int curr = 0;
        int res = 0;
        for(int num : nums) {
            if(num==0) {
                res = Math.max(res, curr);
                curr = 0;
            }else {
                curr++;
            }
        }
        res = Math.max(res, curr);
        return res;
    }

}
