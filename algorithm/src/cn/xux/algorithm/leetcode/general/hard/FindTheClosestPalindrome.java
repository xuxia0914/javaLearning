package cn.xux.algorithm.leetcode.general.hard;

/**
 * 564. 寻找最近的回文数
 * 给定一个整数 n ，你需要找到与它最近的回文数（不包括自身）。
 * “最近的”定义为两个整数差的绝对值最小。
 *
 * 示例 1:
 * 输入: "123"
 * 输出: "121"
 *
 * 注意:
 * n 是由字符串表示的正整数，其长度不超过18。
 * 如果有多个结果，返回最小的那个。
 */
public class FindTheClosestPalindrome {

    public static void main(String[] args) {
        System.out.println(new FindTheClosestPalindrome().nearestPalindromic("10"));
    }

    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        int len = n.length();
        int leftLen = (len+1)/2;
        boolean odd = len%2==1;
        String leftStr = n.substring(0, leftLen);

        long ans;
        if(Integer.parseInt(leftStr)==Math.pow(10, leftLen-1)) {
            ans = (long)Math.pow(10, len-1)-1;
        }else {
            ans = Long.parseLong(leftToNum(String.valueOf(Integer.parseInt(leftStr)-1), odd));
        }
        long diff = num-ans;

        long num2 = Long.parseLong(leftToNum(String.valueOf(Integer.valueOf(leftStr)), odd));
        long diff2 = Math.abs(num-num2);

        if(num2>num) {
            return String.valueOf(diff<=diff2?ans:num2);
        }else if(num2<num) {
            diff = diff2;
            ans = num2;
        }

        long num3;
        if(Integer.parseInt(leftStr)==Math.pow(10, leftLen)-1) {
            num3 = (long)Math.pow(10, len)+1;
        }else {
            num3 = Long.parseLong(leftToNum(String.valueOf(Integer.parseInt(leftStr)+1), odd));
        }
        long diff3 = num3-num;
        return String.valueOf(diff<=diff3?ans:num3);
    }

    private String leftToNum(String left, boolean odd) {
        if(odd) {
            return left + new StringBuilder(left.substring(0, left.length()-1)).reverse().toString();
        }else {
            return left + new StringBuilder(left).reverse().toString();
        }
    }

}
