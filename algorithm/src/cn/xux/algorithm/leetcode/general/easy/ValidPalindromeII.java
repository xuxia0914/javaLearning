package cn.xux.algorithm.leetcode.general.easy;

/**
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 * 输入: "aba"
 * 输出: True
 *
 * 示例 2:
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 *
 * 注意:
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */
public class ValidPalindromeII {

    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length()-1,  false);
    }

    public boolean validPalindrome(String s, int start, int end, boolean flag) {
        while(start<end) {
            if(s.charAt(start)==s.charAt(end)) {
                start++;
                end--;
            }else if(!flag) {
                return validPalindrome(s, start+1, end, true)||validPalindrome(s, start, end-1, true);
            }else {
                return false;
            }
        }
        return true;
    }

}
