package cn.leetcode.xux.easy;

/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class SqrtX {

    public int mySqrt(int x) {
        return (int)mySqrt((long)x);
    }

    public long mySqrt(long x) {
        long start = 0;
        long end = x;
        long mid;
        while(start<end) {
            mid = (start+end+1)/2;
            if(mid*mid==x) {
                return mid;
            }else if(mid*mid>x) {
                end = mid-1;
            }else {
                start = mid;
            }
        }
        return start;
    }

}
