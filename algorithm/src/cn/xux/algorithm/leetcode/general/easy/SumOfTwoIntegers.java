package cn.xux.algorithm.leetcode.general.easy;

/**
 * 371. 两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 *
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 *
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 */
public class SumOfTwoIntegers {

    public int getSum(int a, int b) {
        while(b!=0) {
            int tmp = a;
            a = a^b;
            b = (tmp&b)<<1;
        }
        return a;
    }

}
