package cn.xux.algorithm.leetcode.general.easy;

/**
 * 504. 七进制数
 * 给定一个整数，将其转化为7进制，并以字符串形式输出。
 *
 * 示例 1:
 * 输入: 100
 * 输出: "202"
 *
 * 示例 2:
 * 输入: -7
 * 输出: "-10"
 * 注意: 输入范围是 [-1e7, 1e7] 。
 */
public class Base7 {

    public String convertToBase7(int num) {
        boolean sign = true;
        if(num<0) {
            sign = false;
            num = -num;
        }
        StringBuilder sb = new StringBuilder();
        while(num>=7) {
            sb.append(num%7);
            num /= 7;
        }
        sb.append(num);
        if(!sign) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

}
