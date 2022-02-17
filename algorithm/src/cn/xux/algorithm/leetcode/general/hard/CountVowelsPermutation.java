package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 1220. 统计元音字母序列的数目
 * 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 *
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：5
 * 解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：10
 * 解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
 * 示例 3：
 *
 * 输入：n = 5
 * 输出：68
 *
 *
 * 提示：
 *
 * 1 <= n <= 2 * 10^4
 */
public class CountVowelsPermutation {

    long mod = 1000000007;

    // O(C^3 * logn) C=5(原音字母的个数)
    public int countVowelPermutation(int n) {
        if(n==1) {
            return 5;
        }
        long[][] factor = new long[][]{
                {0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0}
        };
        long[][] matPow = matPow(factor, n-1);
        long ans = 0;
        for(long[] arr : matPow) {
            for(long num : arr) {
                ans = (ans+num)%mod;
            }
        }
        return (int)ans;
    }

    private long[][] matPow(long[][] mat, int m) {
        if(m==1) {
            return mat;
        }
        long[][] pre = matPow(mat, m/2);
        long[][] ans = matrixMultiply(pre, pre);
        if((m&1)==1) {
            ans = matrixMultiply(ans, mat);
        }
        return ans;
    }

    private long[][] matrixMultiply(long[][] mat1, long[][] mat2) {
        long[][] ans = new long[5][5];
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                for(int k=0;k<5;k++) {
                    ans[i][j] = (ans[i][j]+mat1[i][k]*mat2[k][j])%mod;
                }
            }
        }
        return ans;
    }

    // O(n)
    public int countVowelPermutation1(int n) {
        long[] dp = new long[5];
        Arrays.fill(dp, 1);
        while(n-->1) {
            long[] newDp = new long[5];
            newDp[0] = dp[1];
            newDp[1] = (dp[0]+dp[2])%mod;
            newDp[2] = (dp[0]+dp[1]+dp[3]+dp[4])%mod;
            newDp[3] = (dp[2]+dp[4])%mod;
            newDp[4] = dp[0];
            dp = newDp;
        }
        return (int)((dp[0]+dp[1]+dp[2]+dp[3]+dp[4])%mod);
    }

}
