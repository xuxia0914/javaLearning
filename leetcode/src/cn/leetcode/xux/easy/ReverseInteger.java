package cn.leetcode.xux.easy;

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

    public static String solution(Integer i) {
        boolean isNegtive = false;
        String s = String.valueOf(i);
        if(i<0) {
            isNegtive = true;
            s = s.substring(1);
        }
        char[] chars = s.toCharArray();
        for(int j=0;j<chars.length/2;j++) {
            char temp = chars[j];
            chars[j] = chars[chars.length-j-1];
            chars[chars.length-j-1] = temp;
        }
        String s1 = new String(chars);
        while(s1.charAt(0)=='0') {
            s1 = s1.substring(1);
        }
        return isNegtive?"-"+s1:s1;
    }

    public static void main(String[] args) {
        System.out.println(solution(123));
        System.out.println(solution(-123));
        System.out.println(solution(120));
        System.out.println(solution(-120));
    }

}
