package cn.xux.algorithm.leetcode.general.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 316. 去除重复字母
 * 给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1:
 * 输入: "bcabc"
 * 输出: "abc"
 *
 * 示例 2:
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 *
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicateLetters().removeDuplicateLetters("bbcaac"));
    }

    public String removeDuplicateLetters(String s) {
        if(s==null||s.length()<2) {
            return s;
        }
        Deque<Character> deque = new LinkedList<>();
        int[] cnts = new int[26];
        for(char c : s.toCharArray()) {
            cnts[c-'a']++;
        }
        boolean[] flag = new boolean[26];
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if(flag[c-'a']) {
                cnts[c-'a']--;
                continue;
            }
            while(!deque.isEmpty()&&deque.peekLast()>c&&cnts[deque.peekLast()-'a']>0) {
                char del = deque.pollLast();
                flag[del-'a'] = false;
            }
            deque.offer(c);
            flag[c-'a'] = true;
            cnts[c-'a']--;
        }
        StringBuilder ans = new StringBuilder();
        while(!deque.isEmpty()) {
            ans.append(deque.pollFirst());
        }
        return ans.toString();
    }

}
