package cn.leetcode.xux.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
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
