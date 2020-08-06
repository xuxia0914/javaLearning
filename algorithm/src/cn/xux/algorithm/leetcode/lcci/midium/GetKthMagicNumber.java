package cn.xux.algorithm.leetcode.lcci.midium;

/**
 * 面试题 17.09. 第 k 个数
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。注意，不是必须有这些素因子，而是必须不包含其他的素因子。
 * 例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 * 输入: k = 5
 * 输出: 9
 */
public class GetKthMagicNumber {

    public int getKthMagicNumber(int k) {
        if(k<0) {
            return -1;
        }
        int[] dp = new int[k];
        dp[0] = 1;
        int idx3 = 0;
        int idx5 = 0;
        int idx7 = 0;
        for(int i=1;i<k;i++) {
            dp[i] = Math.min(dp[idx3]*3, Math.min(dp[idx5]*5, dp[idx7]*7));
            idx3 += dp[i]==dp[idx3]*3?1:0;
            idx5 += dp[i]==dp[idx5]*5?1:0;
            idx7 += dp[i]==dp[idx7]*7?1:0;
        }
        return dp[k-1];
    }

}
