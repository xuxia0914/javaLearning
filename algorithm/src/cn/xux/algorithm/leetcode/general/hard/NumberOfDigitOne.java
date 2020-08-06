package cn.xux.algorithm.leetcode.general.hard;

/**
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 *
 * 示例:
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
 */
public class NumberOfDigitOne {

    public static void main(String[] args) {
        System.out.println(new NumberOfDigitOne().countDigitOne(10));
    }

    public int countDigitOne(int n) {
        int right = 0;
        int curr = n%10;
        int left = n/10;
        int bit = 1;
        int ans = 0;
        while(curr>0||left>0) {
            if(curr>1) {
                ans += (left+1)*bit;
            }else if(curr==1) {
                ans += left*bit+right+1;
            }else {
                ans += left*bit;
            }
            right += bit*curr;
            curr = left%10;
            left /= 10;
            bit *= 10;
        }
        return ans;
    }

}
