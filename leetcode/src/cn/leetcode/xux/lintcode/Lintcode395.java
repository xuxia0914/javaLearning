package cn.leetcode.xux.lintcode;

/**
 * 395. 硬币排成线 II
 * 中文English
 * 有 n 个不同价值的硬币排成一条线。两个参赛者轮流从 左边 依次拿走 1 或 2 个硬币，直到没有硬币为止。
 * 计算两个人分别拿到的硬币总价值，价值高的人获胜。
 *
 * 请判定 先手玩家 必胜还是必败?
 *
 * 若必胜, 返回 true, 否则返回 false.
 *
 * 样例
 * 样例 1:
 *
 * 输入: [1, 2, 2]
 * 输出: true
 * 解释: 先手玩家直接拿走两颗硬币即可.
 * 样例 2:
 *
 * 输入: [1, 2, 4]
 * 输出: false
 * 解释: 无论先手拿一个还是两个, 后手可以拿完, 然后总价值更高.
 */
public class Lintcode395 {

    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here
        if(values==null||values.length==0) {
            return false;
        }
        if(values.length<3) {
            return true;
        }
        int n = values.length;
        int[] dp = new int[n];
        dp[n-1] = values[n-1];
        dp[n-2] = values[n-1]+values[n-2];
        dp[n-3] = values[n-2]+values[n-3];
        int sum = values[n-1]+values[n-2]+values[n-3];
        for(int i=n-4;i>=0;i--) {
            sum += values[i];
            dp[i] = Math.max(sum-dp[i+1], sum-dp[i+2]);
        }
        return dp[0]*2>sum;
    }

}
