package cn.leetcode.xux.midium;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        if(n==2||n==3) {
            return n-1;
        }
        int mod = 1000000007;
        int m = n/3-1;
        long result = 1;
        while(m-->0) {
            result = result*3%mod;
        }
        if(n%3==0) {
            return (int)result*3%mod;
        }else if(n%3==1) {
            return (int)result*4%mod;
        }else {
            return (int)result*6%mod;
        }
    }

}
