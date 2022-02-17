package cn.xux.algorithm.leetcode.general.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * <p>
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        int[] tar = new int[58];
        int cnt = 0;
        for (char c : t.toCharArray()) {
            if (tar[c - 'A']++ == 0) {
                cnt++;
            }
        }
        int n = s.length();
        int[] curr = new int[58];
        int left = 0;
        int right = 0;
        while (right < n && cnt > 0) {
            int idx = s.charAt(right++) - 'A';
            if (++curr[idx] == tar[idx] && --cnt == 0) {
                break;
            }
        }
        if (cnt > 0) {
            return "";
        }
        int ans1 = 0;
        int ans2 = right;
        while (right <= n) {
            while (left < right && curr[s.charAt(left) - 'A'] - 1 >= tar[s.charAt(left) - 'A']) {
                curr[s.charAt(left++) - 'A']--;
            }
            if (right - left < ans2 - ans1) {
                ans1 = left;
                ans2 = right;
            }
            if (right < n) {
                curr[s.charAt(right) - 'A']++;
            }
            right++;
        }
        return s.substring(ans1, ans2);
    }

    public String minWindow1(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        int start = -1;
        int end = s.length();
        int left = 0;
        int right = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> mem = new HashMap<>();
        while (right < s.length()) {
            while (!helper(mem, map) && right < s.length()) {
                mem.put(s.charAt(right), mem.getOrDefault(s.charAt(right), 0) + 1);
                right++;
            }
            while (helper(mem, map) && left <= right) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }
                mem.put(s.charAt(left), mem.get(s.charAt(left)) - 1);
                left++;
            }
        }
        return start == -1 ? "" : s.substring(start, end);
    }

    private boolean helper(Map<Character, Integer> mem, Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (mem.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

}
