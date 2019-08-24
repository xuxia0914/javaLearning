package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * Example 1:
 * Input: “abcabcbb”
 * Output: 3
 * Explanation: The answer is “abc”, with the length of 3.
 * Example 2:
 * Input: “bbbbb”
 * Output: 1
 * Explanation: The answer is “b”, with the length of 1.
 * Example 3:
 * Input: “pwwkew”
 * Output: 3
 * Explanation: The answer is “wke”, with the length of 3.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static int solution(String s) {
        if(s==null||"".equals(s)) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int result = 0;
        int i=0;    //表示当前无重复子串的起始下标
        for(int j=0;j<s.length();j++) {
            Character curr = s.charAt(j);
            if(map.containsKey(curr)) {
                i = Math.max(map.get(curr), i);
            }
            result = Math.max(result, j-i+1);
            map.put(curr, j+1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("abcabcbb"));
        System.out.println(solution("bbbbb"));
        System.out.println(solution("pwwkew"));
    }

}
