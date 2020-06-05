package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 140. 单词拆分 II
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 *
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * 示例 2：
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 */
public class WordBreakII {

    Map<String, List<String>> mem = new HashMap<>();

    //递归
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if(s.equals("")) {
            result.add("");
            return result;
        }
        if(mem.containsKey(s)) {
            return mem.get(s);
        }
        for(int i=1;i<=s.length();i++) {
            String tmp = s.substring(0, i);
            if(wordDict.contains(tmp)) {
                List<String> postResult = wordBreak(s.substring(i), wordDict);
                for(String str : postResult) {
                    if(str.equals("")) {
                        result.add(tmp);
                    }else {
                        result.add(tmp+" "+str);
                    }
                }
            }
        }
        mem.put(s, result);
        return result;
    }

    //动态规划
    public List<String> wordBreak1(String s, List<String> wordDict) {
        int len = s.length();
        List<String>[] dp = new List[len+1];
        List<String> list0 = new ArrayList<>();
        list0.add("");
        dp[0] = list0;
        for(int i=1;i<=len;i++) {
            for(int j=0;j<i;j++) {
                if(dp[j]!=null) {
                    String curr = s.substring(j, i);
                    if(wordDict.contains(curr)) {
                        List<String> list = null;
                        if(dp[i]==null) {
                            list = new ArrayList<String>();
                        }else {
                            list = dp[i];
                        }
                        for(String str : dp[j]) {
                            if("".equals(str)) {
                                list.add(curr);
                            }else {
                                list.add(str+" "+curr);
                            }
                        }
                        dp[i] = list;
                    }
                }
            }
        }
        return dp[len]==null?new ArrayList<>():dp[len];
    }

    public static void main(String[] args) {
        /*List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        System.out.println(new WordBreakII().wordBreak("catsanddog", wordDict));
        System.out.println(new WordBreakII().wordBreak("catsandog", wordDict));*/
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        System.out.println(new WordBreakII().wordBreak(s, wordDict));
        /**
         * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
         * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
         */
    }

}
