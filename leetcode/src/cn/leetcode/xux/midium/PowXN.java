package cn.leetcode.xux.midium;

/**
 * Implement pow(x, n), which calculates x raised to the power n(xn).
 * Example 1:
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 * Note:
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 *
 */
public class PowXN {

    public double myPow(double x, int n) {
        if(n==0) {
            return 1;
        }
        if(n<0) {
            n = -n;
            x = 1/x;
        }
        return helper(x, n);
    }

    public double helper(double x, int n) {
        if(n==0) {
            return 1;
        }
        double half = helper(x, n/2);
        if(n%2==0) {
            return half*half;
        }else {
            return half*half*x;
        }
    }

}
