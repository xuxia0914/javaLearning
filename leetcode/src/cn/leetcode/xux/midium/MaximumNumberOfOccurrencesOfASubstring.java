package cn.leetcode.xux.midium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1297. 子串的最大出现次数
 * 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
 *
 * 示例 1：
 * 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * 输出：2
 * 解释：子串 "aab" 在原字符串中出现了 2 次。
 * 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 *
 * 示例 2：
 * 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * 输出：2
 * 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 *
 * 示例 3：
 * 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * 输出：3
 *
 * 示例 4：
 * 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * 输出：0
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s 只包含小写英文字母。
 */
public class MaximumNumberOfOccurrencesOfASubstring {

    //"aababcaab"
    //2
    //3
    //4
    public static void main(String[] args) {
        System.out.println(new MaximumNumberOfOccurrencesOfASubstring()
                .maxFreq1("aababcaab", 2, 3, 4));
    }

    //只需要判断minSize的子串就行了
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int len = s.length();
        Map<String, Integer> map = new HashMap<>();
        int result = 0;
        for(int i=0;i+minSize<=len;i++) {
            String curr = s.substring(i, i+minSize);
            Set<Character> set = new HashSet<>();
            for(char c : curr.toCharArray()) {
                set.add(c);
            }
            if(set.size()<=maxLetters) {
                int currLetters = map.getOrDefault(curr, 0)+1;
                map.put(curr, currLetters);
                result = Math.max(result, currLetters);
            }
        }
        return result;
    }

    //只需要判断minSize的子串就行了
    public int maxFreq1(String s, int maxLetters, int minSize, int maxSize) {
        int len = s.length();
        int[] cnts = new int[26];
        int letters = 0;
        int left = 0;
        int right;
        for(right=0;right-left+1<=minSize;right++) {
            char c = s.charAt(right);
            cnts[c-'a']++;
            if(cnts[c-'a']==1) {
                letters++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        int result = 0;
        if(letters<=maxLetters) {
            map.put(s.substring(left, right), 1);
            result = 1;
        }
        while(right<len) {
            char lc = s.charAt(left);
            cnts[lc-'a']--;
            if(cnts[lc-'a']==0) {
                letters--;
            }
            left++;
            char rc = s.charAt(right);
            cnts[rc-'a']++;
            if(cnts[rc-'a']==1) {
                letters++;
            }
            if(letters<=maxLetters) {
                String curr = s.substring(left, right+1);
                int currLetters = map.getOrDefault(curr, 0)+1;
                map.put(curr, currLetters);
                result = Math.max(result, currLetters);
            }
            right++;
        }
        return result;
    }

}
