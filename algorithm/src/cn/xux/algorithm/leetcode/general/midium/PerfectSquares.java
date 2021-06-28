package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 279. 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class PerfectSquares {

    public int numSquares(int n) {
        if(n<1) {
            return 0;
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0;i<=n;i++) {
            for(int j=1;i+j*j<=n;j++) {
                dp[i+j*j] = Math.min(dp[i+j*j], dp[i]+1);
            }
        }
        return dp[n];
    }

    // 四平方和定理
    public int numSquares1(int n) {
        if(isFourSquare(n)) {
            return 4;
        }
        if(isOneSquare(n)) {
            return 1;
        }
        if(isTwoSquare(n)) {
            return 2;
        }
        return 3;
    }

    private boolean isOneSquare(int n) {
        return n==(int)Math.pow((int)Math.sqrt(n), 2);
    }

    private boolean isTwoSquare(int n) {
        for(int i=0;i<=Math.sqrt(n);i++) {
            int otherPow = n-i*i;
            if(otherPow==(int)Math.pow((int)Math.sqrt(otherPow), 2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFourSquare(int n) {
        while(n%4==0) {
            n /= 4;
        }
        return n%8==7;
    }

}
