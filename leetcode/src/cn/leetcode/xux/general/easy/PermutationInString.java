package cn.leetcode.xux.general.easy;

import java.util.Arrays;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 * Example 1:
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * Note:
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if(s1==null||s2==null||s1.length()>s2.length()) {
            return false;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        int[] array1 = new int[26];
        for(int i=0;i<len1;i++) {
            array1[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<=len2-len1;i++) {
            int[] array2 = new int[26];
            for(int j=0;j<len1;j++) {
                array2[s2.charAt(i+j)-'a']++;
            }
            if(Arrays.equals(array1, array2)) {
                return true;
            }
        }
        return false;
    }

}
