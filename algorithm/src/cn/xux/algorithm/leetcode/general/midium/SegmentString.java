package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class SegmentString {

    public static boolean solution1(String s, List<String> wordDict) {
        int start=0,end=1;
        boolean[] useds = new boolean[wordDict.size()];
        while(end<s.length()) {
            if(wordDict.contains(s.substring(start, end))) {
                useds[wordDict.indexOf(s.substring(start, end))] = true;
                start = end;
                end++;
            }else {
                end++;
            }
        }
        if(wordDict.contains(s.substring(start, end))) {
            useds[wordDict.indexOf(s.substring(start, end))] = true;
            boolean result = true;
            for(boolean b : useds) {
                result &=b;
            }
            return result;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<String>();
//        String s = "leetcode";
//        wordDict.add("leet");
//        wordDict.add("code");
//        wordDict.add("code");
//        wordDict.add("code");
//        wordDict.add("code");
//        String s = "applepenapple";
//        wordDict.add("apple");
//        wordDict.add("pen");
        String s = "catsandog";
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        System.out.println(solution1(s, wordDict));
    }

}
