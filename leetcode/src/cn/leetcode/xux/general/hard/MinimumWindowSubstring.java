package cn.leetcode.xux.general.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if(s==null||t==null||s.length()<t.length()) {
            return "";
        }
        int start = -1;
        int end = s.length();
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        Map<Character, Integer> mem = new HashMap<>();
        while(right<s.length()) {
            while(!helper(mem, map)&&right<s.length()) {
                mem.put(s.charAt(right), mem.getOrDefault(s.charAt(right), 0)+1);
                right++;
            }
            while(helper(mem, map)&&left<=right) {
                if(right-left<end-start) {
                    start = left;
                    end = right;
                }
                mem.put(s.charAt(left), mem.get(s.charAt(left))-1);
                left++;
            }
        }
        return start==-1?"":s.substring(start, end);
    }

    private boolean helper(Map<Character, Integer> mem, Map<Character, Integer> map) {
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(mem.getOrDefault(entry.getKey(), 0)<entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
