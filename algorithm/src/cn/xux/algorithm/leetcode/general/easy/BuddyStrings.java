package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;

/**
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 * Input: A = "ab", B = "ab"
 * Output: false
 * Example 3:
 * Input: A = "aa", B = "aa"
 * Output: true
 * Example 4:
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 * Input: A = "", B = "aa"
 * Output: false
 * Note:
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist only of lowercase letters.
 */
public class BuddyStrings {

    public boolean buddyStrings(String A, String B) {
        if(A==null||B==null||A.length()!=B.length()) {
            return false;
        }
        int len = A.length();
        if(A.equals(B)) {
            char[] chars = A.toCharArray();
            Arrays.sort(chars);
            for(int i=0;i<len-1;i++) {
                if(chars[i]==chars[i+1]) {
                    return true;
                }
            }
        }
        char a ='1' ,b = '1';
        boolean flag1 = false;
        boolean flag2 = false;
        for(int i=0;i<len;i++) {
            if(A.charAt(i)!=B.charAt(i)) {
                if(flag2) {
                    return false;
                }else {
                    if(flag1) {
                        if(a==B.charAt(i)&&b==A.charAt(i)) {
                            flag2 = true;
                        }else {
                            return false;
                        }
                    }else {
                        a = A.charAt(i);
                        b = B.charAt(i);
                        flag1 = true;
                    }
                }
            }
        }
        return flag2;
    }

}
