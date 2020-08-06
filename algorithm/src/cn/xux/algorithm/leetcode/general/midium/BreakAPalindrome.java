package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1328. 破坏回文串
 * 给你一个回文字符串 palindrome ，请你将其中 一个 字符用任意小写英文字母替换，
 * 使得结果字符串的字典序最小，且 不是 回文串。
 * 请你返回结果字符串。如果无法做到，则返回一个空串。
 *
 * 示例 1：
 * 输入：palindrome = "abccba"
 * 输出："aaccba"
 *
 * 示例 2：
 * 输入：palindrome = "a"
 * 输出：""
 *
 * 提示：
 * 1 <= palindrome.length <= 1000
 * palindrome 只包含小写英文字母。
 */
public class BreakAPalindrome {

    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        int end = len/2;
        for(int i=0;i<end;i++) {
            if(palindrome.charAt(i)!='a') {
                return palindrome.substring(0, i)+'a'+palindrome.substring(i+1);
            }
        }
        if(len>1) {
                return palindrome.substring(0,len-1)+'b';
        }else {
            return "";
        }
    }

}
