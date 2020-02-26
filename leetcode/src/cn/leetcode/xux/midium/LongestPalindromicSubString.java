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

    /**Manacher算法*/
    public String longestPalindrome(String s) {
        if(s==null||s.length()<2) {
            return s;
        }
        //预处理字符串
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for(char c : s.toCharArray()) {
            sb.append(c).append('#');
        }
        String str = sb.toString();
        int len = str.length();
        //以i为中心的回文串的半径
        int[] dp = new int[len];
        //当前所有回文能到达的最大右边界
        int max = 0;
        //当前所有回文能到达最大右边界的回文的中心
        int id = 0;
        //最大回文的中心和半径
        int[] res = new int[2];
        for(int i=0;i<len;i++) {
            //manacher算法思想的关键
            if(i<max) {
                dp[i] = Math.min(dp[2*id-i], max-i);
            }else {
                dp[i] = 1;
            }
            //暴力扩展
            while(i-dp[i]>=0&&i+dp[i]<len&&str.charAt(i-dp[i])==str.charAt(i+dp[i])) {
                dp[i]++;
            }
            if(i+dp[i]>max) {
                max = i+dp[i];
                id = i;
            }
            if(dp[i]>res[1]) {
                res[0] = i;
                res[1] = dp[i];
            }
        }
        StringBuilder result = new StringBuilder();
        for(int i=res[0]-res[1]+1;i<res[0]+res[1];i++) {
            if(str.charAt(i)!='#') {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    public String longestPalindrome1(String s) {
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
        LongestPalindromicSubString lps = new LongestPalindromicSubString();
        //System.out.println(lps.longestPalindrome("ababad"));
        //System.out.println(lps.longestPalindrome("dcbbcd"));
        System.out.println(lps.longestPalindrome("cbdfghbd"));
    }

}
