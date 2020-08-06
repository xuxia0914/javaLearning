package cn.xux.algorithm.leetcode.general.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串第一个唯一字符
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * Examples:
 * s = "leetcode"
 * return 0.
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {

    public static int solution1(String s) {
        if(s==null||s.length()==0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i=0;i<s.length();i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        for(int i=0;i<s.length();i++) {
            if(map.get(s.charAt(i))==1) {
                return i;
            }
        }
        return -1;
    }

    public static int solution2(String s) {
        if(s==null||s.length()==0) {
            return -1;
        }
        int[] array = new int[26];
        for(int i=0;i<s.length();i++) {
            array[s.charAt(i)-'a']++;
        }
        for(int i=0;i<s.length();i++) {
            if(array[s.charAt(i)-'a']==1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution2("leetcode"));
        System.out.println(solution2("loveleetcode"));
    }

}
