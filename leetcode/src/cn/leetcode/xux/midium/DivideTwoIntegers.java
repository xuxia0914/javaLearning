package cn.leetcode.xux.midium;

/**
 * 29. 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 *
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 *
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if(dividend==0) {
            return 0;
        }
        if(divisor==1) {
            return dividend;
        }
        if(divisor==-1) {
            return dividend>Integer.MIN_VALUE?-dividend:Integer.MAX_VALUE;
        }
        boolean sign = true;
        long a = dividend;
        long b = divisor;
        if(a<0) {
            a = -a;
            sign = !sign;
        }
        if(b<0) {
            b = -b;
            sign = !sign;
        }
        int res = (int)divide(a, b);
        return sign?res:-res;
    }

    public long divide(long dividend, long divisor) {
        if(dividend<divisor) {
            return 0;
        }
        long cnt = 1;
        long tb = divisor;
        while(tb+tb<dividend) {
            tb += tb;
            cnt += cnt;
        }
        return cnt+divide(dividend-tb, divisor);
    }

    public int divide1(int dividend, int divisor) {
        if(divisor==0) {
            return Integer.MAX_VALUE;
        }
        if(dividend==0) {
            return 0;
        }
        long res = divide((long)dividend, (long)divisor);
        return (long)Integer.MAX_VALUE<res?Integer.MAX_VALUE:(int)res;
    }

    public long divide1(long dividend, long divisor) {
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
