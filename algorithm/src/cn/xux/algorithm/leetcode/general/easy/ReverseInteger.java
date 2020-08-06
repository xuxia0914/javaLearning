package cn.xux.algorithm.leetcode.general.easy;

/**
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 *  示例 2:
 * 输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 */
public class ReverseInteger {

    public int reverse(int x) {
        if(x==Integer.MIN_VALUE) {
            return 0;
        }
        if(x<0) {
            return -reverse(-x);
        }
        long res = 0;
        long curr = x;
        while(curr>0) {
            long pop = curr%10;
            curr /= 10;
            res = res*10+pop;
        }
        return res>Integer.MAX_VALUE?0:(int)res;
    }

}
