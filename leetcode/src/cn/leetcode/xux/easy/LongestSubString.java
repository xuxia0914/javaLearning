package cn.leetcode.xux.easy;

/**
 * 题目描述：
 * 给定一个字符串，找出不含有重复字符的 最长子串 的长度。
 *
 * 示例：
 * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
 * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
 * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串
 */
public class LongestSubString {

    public static String solve(String s) {
        String result = "";
        if(s==null||s.length()==0) {
            return "";
        }
        for(int i=0;i<s.length();i++) {
            String curr = s.charAt(i)+"";
            for(int j=i+1;j<s.length();j++) {
                if(curr.contains(s.charAt(j)+"")) {
                    break;
                }
                curr += s.charAt(j);
            }
            if(result.length()<curr.length()) {
                result = curr;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve("abcabcbb"));
        System.out.println(solve("bbbbb"));
        System.out.println(solve("pwwkew"));
    }

}
