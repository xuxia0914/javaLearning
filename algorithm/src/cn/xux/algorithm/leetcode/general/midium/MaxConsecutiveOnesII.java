package cn.xux.algorithm.leetcode.general.midium;

/**
 * 487. 最大连续1的个数 II
 * 给定一个二进制数组，你可以最多将 1 个 0 翻转为 1，找出其中最大连续 1 的个数。
 *
 * 示例 1：
 * 输入：[1,0,1,1,0]
 * 输出：4
 * 解释：翻转第一个 0 可以得到最长的连续 1。
 *      当翻转以后，最大连续 1 的个数为 4。
 *
 * 注：
 * 输入数组只包含 0 和 1.
 * 输入数组的长度为正整数，且不超过 10,000
 *
 * 进阶：
 * 如果输入的数字是作为 无限流 逐个输入如何处理？换句话说，内存不能存储下所有从流中输入的数字。您可以有效地解决吗？
 */
public class MaxConsecutiveOnesII {

    public int findMaxConsecutiveOnes(int[] nums) {
        int[] dp = new int[2];
        dp[0] = nums[0];
        dp[1] = 1;
        int res = 0;
        for(int i=1;i<nums.length;i++) {
            int num = nums[i];
            if(num==1) {
                dp[0] = dp[0] + 1;
                dp[1] = dp[1] + 1;
            }else {
                res = Math.max(res, dp[1]);
                dp[1] = dp[0] + 1;
                dp[0] = 0;
            }
        }
        res = Math.max(res, dp[1]);
        return res;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesII mco = new MaxConsecutiveOnesII();
        System.out.println(mco.findMaxConsecutiveOnes(new int[]{1,0,1,1,0}));
        System.out.println(mco.findMaxConsecutiveOnes(new int[]{0,1,1,1,1}));
        System.out.println(mco.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1,1,1,1}));
        System.out.println(mco.findMaxConsecutiveOnes(new int[]{1,0,0,1,1,0,0,1,1,1,1}));
    }

}
