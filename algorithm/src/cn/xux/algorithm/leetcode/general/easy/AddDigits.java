package cn.xux.algorithm.leetcode.general.easy;

/**
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
 *
 * 示例:
 * 输入: 38
 * 输出: 2
 * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
 *
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 */
public class AddDigits {

    public int addDigits(int num) {
        if(num<10) {
            return num;
        }
        int res = 0;
        while(num>0) {
            res += num%10;
            num /= 10;
        }
        return addDigits(res);
    }

    /**
     * 思路:一个数如果是9的倍数，那么这个数的所有位数之和也是9的倍数，递推可知当n%9==0时，f(n)==9；
     * 此时每当n加1时，f(n)也加1,f(n+x)=x (x<9)，即当n%9!=0时，f(n)=n%9,
     * 最后再加上n=0的特殊情况。
     */
    public int addDigits1(int num) {
        return num!=0&&num%9==0?9:num%9;
    }

}
