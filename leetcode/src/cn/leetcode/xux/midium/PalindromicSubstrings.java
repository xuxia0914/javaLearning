package cn.leetcode.xux.midium;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 *
 * 注意:
 * 输入的字符串长度不会超过1000。
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        if(s==null||s=="") {
            return 0;
        }
        int len = s.length();
        int result = 0;
        for(int i=0;i<len;i++) {
            for(int j=0;i-j>=0&&i+j<len;j++) {
                if(s.charAt(i-j)==s.charAt(i+j)) {
                    result++;
                }else {
                    break;
                }
            }
        }
        for(int i=0;i>=0&&i+1<len;i++) {
            for(int j=0;i-j>=0&&i+1+j<len;j++) {
                if(s.charAt(i-j)==s.charAt(i+1+j)) {
                    result++;
                }else {
                    break;
                }
            }
        }
        return result;
    }

}
