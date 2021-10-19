package cn.xux.algorithm.leetcode.general.easy;

import java.util.Arrays;

/**
 * 242. 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 */
public class ValidAnagram {

    public static boolean solution1(String s, String t) {
        if(s.length()!=t.length()) {
            return false;
        }
        int[] cnt1 = new int[26];
        for(char c : s.toCharArray()) {
            cnt1[c-'a']++;
        }
        int[] cnt2 = new int[26];
        for(char c : t.toCharArray()) {
            cnt2[c-'a']++;
        }
        for(int i=0;i<26;i++) {
            if(cnt1[i]!=cnt2[i]) {
                return false;
            }
        }
        return true;
    }

}
