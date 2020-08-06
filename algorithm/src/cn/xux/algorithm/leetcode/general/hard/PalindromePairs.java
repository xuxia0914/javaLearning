package cn.xux.algorithm.leetcode.general.hard;

import java.util.*;

/**
 * 336. 回文对
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，
 * 使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1:
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * 示例 2:
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0;i<words.length;i++) {
            map.put(words[i], i);
        }
        for(int i=0;i<words.length;i++) {
            String word = words[i];
            String revStr = new StringBuilder(word).reverse().toString();
            int j = map.getOrDefault(revStr, -1);
            if(j!=-1&&j!=i) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                tmp.add(j);
                ans.add(tmp);
            }
            for(String preStr : validPrefix(word)) {
                revStr = new StringBuilder(preStr).reverse().toString();
                j = map.getOrDefault(revStr, -1);
                if(j!=-1&&j!=i) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    ans.add(tmp);
                }
            }
            for(String sufStr : validSuffix(word)) {
                revStr = new StringBuilder(sufStr).reverse().toString();
                j = map.getOrDefault(revStr, -1);
                if(j!=-1&&j!=i) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(j);
                    tmp.add(i);
                    ans.add(tmp);
                }
            }
        }
        return ans;
    }

    private List<String> validPrefix(String word) {
        int len = word.length();
        List<String> ans = new ArrayList<>();
        for(int i=len-1;i>=0;i--) {
            if(isPalindrome(word, i, len-1)) {
                ans.add(word.substring(0, i));
            }
        }
        return ans;
    }

    private List<String> validSuffix(String word) {
        int len = word.length();
        List<String> ans = new ArrayList<>();
        for(int i=0;i<len;i++) {
            if(isPalindrome(word, 0, i)) {
                ans.add(word.substring(i+1, len));
            }
        }
        return ans;
    }

    private boolean isPalindrome(String word, int start, int end) {
        while(start<end) {
            if(word.charAt(start)!=word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
