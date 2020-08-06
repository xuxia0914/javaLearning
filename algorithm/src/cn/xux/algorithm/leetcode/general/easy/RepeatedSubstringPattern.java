package cn.xux.algorithm.leetcode.general.easy;

/**
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 * Example 1:
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 * Input: "aba"
 * Output: False
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        String tmp;
        for(int i=2;i<=len;i++) {
            if(len%i==0) {
                tmp = s.substring(0, len/i);
                int j;
                for(j=0;j<len;j+=len/i) {
                    if(!s.substring(j, j+len/i).equals(tmp)) {
                        break;
                    }
                }
                if(j==len) {
                    return true;
                }
            }
        }
        return false;
    }

}
