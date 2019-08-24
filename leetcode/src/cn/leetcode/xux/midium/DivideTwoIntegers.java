package cn.leetcode.xux.midium;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication,
 * division and mod operator.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 *
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem,
 * assume that your function returns 231 − 1 when the division result overflows.
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if(divisor==0) {
            return Integer.MAX_VALUE;
        }
        if(dividend==0) {
            return 0;
        }
        long res = divide((long)dividend, (long)divisor);
        return (long)Integer.MAX_VALUE<res?Integer.MAX_VALUE:(int)res;
    }

    public long divide(long dividend, long divisor) {
        boolean sign = true;
        if(dividend<0) {
            sign = !sign;
            dividend = -dividend;
        }
        if(divisor<0) {
            sign = !sign;
            divisor = -divisor;
        }
        long bitNum = 0;
        long tmp = divisor;
        while(tmp>0) {
            tmp = tmp>>1;
            bitNum++;
        }
        long res = 0;
        while(dividend>=divisor&&bitNum<=32) {
            if(divisor<<32-bitNum <= dividend) {
                res += (long)1<<32-bitNum;
                dividend -= divisor<<32-bitNum;
            }
            bitNum++;
        }
        return sign?res:-res;
    }

    public static void main(String[] args) {
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divide(-2147483648, -1));
    }

}
