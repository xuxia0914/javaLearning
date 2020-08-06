package cn.xux.algorithm.leetcode.general.easy;

/**
 * 400. 第N个数字
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 *
 * 注意:
 * n是正数且在32为整形范围内 ( n < 231)。
 *
 * 示例 1:
 * 输入:3
 * 输出:3
 *
 * 示例 2:
 * 输入:11
 * 输出:0
 *
 * 说明:
 * 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 */
public class NthDigit {

    public static int findNthDigit(int n) {
        if(n<10) {
            return n;
        }
        int num = 9;
        int len = 1;
        while(n-num*len>0&&len<9) {
            n -= num*len;
            num *= 10;
            len++;
        }
        int tar = num/9 + (n-1)/len;
        int index = (n-1)%len;
        return (tar+"").charAt(index)-'0';
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(1000000000));   //1
//        System.out.println(findNthDigit(11));
    }

}
