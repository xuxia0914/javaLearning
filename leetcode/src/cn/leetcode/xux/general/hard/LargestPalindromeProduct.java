package cn.leetcode.xux.general.hard;

/**
 * 479. 最大回文数乘积
 * 你需要找到由两个 n 位数的乘积组成的最大回文数。
 * 由于结果会很大，你只需返回最大回文数 mod 1337得到的结果。
 *
 * 示例:
 * 输入: 2
 * 输出: 987
 * 解释: 99 x 91 = 9009, 9009 % 1337 = 987
 *
 * 说明:
 * n 的取值范围为 [1,8]。
 */
public class LargestPalindromeProduct {

    public int largestPalindrome(int n) {
        if(n==1) {
            return 9;
        }
        long max = (long)Math.pow(10, n)-1;
        for(long i=max;i>=max/10;i--) {
            String s1 = String.valueOf(i);
            long product = Long.parseLong(s1+new StringBuilder(s1).reverse().toString());
            for(long x=max;x*x>=product;x--) {
                if(product%x==0) {
                    return (int)(product%1337);
                }
            }
        }
        return -1;
    }

}
