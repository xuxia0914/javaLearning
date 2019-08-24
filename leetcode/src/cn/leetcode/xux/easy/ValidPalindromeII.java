package cn.leetcode.xux.easy;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
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
