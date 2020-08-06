package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串
 * 给定一个字符串 s 和一些长度相同的单词 words。
 * 找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 * 输入：
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * 输出：[]
 */
public class SubstringWithConcatenationOfAllWords {

    public static void main(String[] args) {
        System.out.println(new SubstringWithConcatenationOfAllWords()
                .findSubstring("a", new String[]{"a"}));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if(s==null||words==null||s.length()==0
                ||words.length==0||words[0].length()==0) {
            return ans;
        }
        int len = s.length();
        int n = words.length;
        int l = words[0].length();
        int tarLen = n*l;
        Map<String, Integer> tarMap = new HashMap<>();
        for(String word : words) {
            tarMap.put(word, tarMap.getOrDefault(word, 0)+1);
        }
        for(int start=0;start<=Math.min(l-1, len-tarLen);start++) {
            int left = start;
            int right = start;
            Map<String, Integer> currMap = new HashMap<>();
            int count = 0;
            while(right+l<=len) {
                String currWord = s.substring(right, right+l);
                currMap.put(currWord, currMap.getOrDefault(currWord, 0)+1);
                count++;
                right += l;
                while(currMap.getOrDefault(currWord, 0)>tarMap.getOrDefault(currWord, 0)) {
                    String delWord = s.substring(left, left+l);
                    count--;
                    left += l;
                    currMap.put(delWord, currMap.get(delWord)-1);
                }
                if(count==n) {
                    ans.add(left);
                }
            }
        }
        return ans;
    }

}
