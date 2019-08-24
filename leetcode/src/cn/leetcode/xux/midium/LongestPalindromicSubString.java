package cn.leetcode.xux.midium;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */

public class LongestPalindromicSubString {

    public static String solution(String s) {
        if(s==null||s.length()<2) {
            return s;
        }
        //回文子串长度为奇数
        int len = s.length();
        int left, right;
        String res = "";
        for(int i=0;i<len;i++) {
            left=i;
            right=i;
            while(left>=0&&right<=len-1&&s.charAt(left)==s.charAt(right)) {
                left--;
                right++;
            }
            if(right-left-1>res.length()) {
                res = s.substring(left+1, right);
            }
        }
        //回文子串长度为偶数
        for(int i=0;i<len;i++) {
            left=i;
            right=i+1;
            while(left>=0&&right<=len-1&&s.charAt(left)==s.charAt(right)) {
                left--;
                right++;
            }
            if(right-left-1>res.length()) {
                res = s.substring(left+1, right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution("ababad"));
        System.out.println(solution("dcbbcd"));
        System.out.println(solution("cbdfghbd"));
    }

}
