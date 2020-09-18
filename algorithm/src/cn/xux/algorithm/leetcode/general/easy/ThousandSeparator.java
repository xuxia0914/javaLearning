package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1556. 千位分隔数
 * 给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。
 *
 * 示例 1：
 * 输入：n = 987
 * 输出："987"
 *
 * 示例 2：
 * 输入：n = 1234
 * 输出："1.234"
 *
 * 示例 3：
 * 输入：n = 123456789
 * 输出："123.456.789"
 *
 * 示例 4：
 * 输入：n = 0
 * 输出："0"
 *
 * 提示：
 * 0 <= n < 2^31
 */
public class ThousandSeparator {

    public static void main(String[] args) {
        System.out.println(-10%-3);
        System.out.println(-10/-3);
    }

    public String thousandSeparator(int n) {
        if(n==0) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        if(n<0) {
            ans.append('-');
            n  = -n;
        }
        int cnt = 0;
        while(n>0) {
            if(cnt==3) {
                ans.append('.');
                cnt = 0;
            }
            ans.append(n%10);
            n /= 10;
            cnt++;
        }
        return ans.reverse().toString();
    }

}
