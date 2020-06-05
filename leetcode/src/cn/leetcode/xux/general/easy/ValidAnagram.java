package cn.leetcode.xux.general.easy;

import java.util.Arrays;

/**
 * 验证变位词
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * Note:
 * You may assume the string contains only lowercase alphabets.
 */
public class ValidAnagram {

    public static boolean solution1(String s, String t) {
        if(s==null||t==null||s.length()!=t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return new String(sChars).equals(new String(tChars));

    }

    public static boolean solution2(String s, String t) {
        if(s==null||t==null||s.length()!=t.length()) {
            return false;
        }
        int[] flags = new int[26];
        for(int i=0;i<s.length();i++) {
            flags[s.charAt(i)-'a']++;
            flags[t.charAt(i)-'a']--;
        }
        for(int i : flags) {
            if(i!=0) {
                return false;
            }
        }
        return true;
    }

}
