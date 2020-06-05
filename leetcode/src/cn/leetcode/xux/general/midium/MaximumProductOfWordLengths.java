package cn.leetcode.xux.general.midium;

/**
 * 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 * 示例 1:
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 *
 * 示例 2:
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 *
 * 示例 3:
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 */
public class MaximumProductOfWordLengths {

    public int maxProduct(String[] words) {
        if(words==null||words.length<2) {
            return 0;
        }
        int n = words.length;
        boolean[][] flags = new boolean[n][26];
        for(int i=0;i<n;i++) {
            for(int j=0;j<words[i].length();j++) {
                flags[i][words[i].charAt(j)-'a'] = true;
            }
        }
        int res = 0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                boolean flag = false;
                for(int k=0;k<26;k++) {
                    if(flags[i][k]&&flags[j][k]) {
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    res = Math.max(res, words[i].length()*words[j].length());
                }
            }
        }
        return res;
    }

}
