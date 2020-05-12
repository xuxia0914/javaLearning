package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 1048. 最长字符串链
 * 给出一个单词列表，其中每个单词都由小写英文字母组成。
 * 如果我们可以在 word1 的任何地方添加一个字母使其变成 word2，
 * 那么我们认为 word1 是 word2 的前身。例如，"abc" 是 "abac" 的前身。
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，
 * 其中 word_1 是 word_2 的前身，word_2 是 word_3 的前身，依此类推。
 * 从给定单词列表 words 中选择单词组成词链，返回词链的最长可能长度。
 *
 * 示例：
 * 输入：["a","b","ba","bca","bda","bdca"]
 * 输出：4
 * 解释：最长单词链之一为 "a","ba","bda","bdca"。
 *
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] 仅由小写英文字母组成。
 */
public class LongestStringChain {

    public static void main(String[] args) {
        System.out.println(new LongestStringChain().longestStrChain(
                new String[]{"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"}
        ));
    }

    public int longestStrChain(String[] words) {
        if(words==null||words.length<2) {
            return words.length;
        }
        int len = words.length;
        int[] dp = new int[len];
        Arrays.sort(words, Comparator.comparingInt(o->o.length()));
        int result = 1;
        for(int i=0;i<len;i++) {
            String curr = words[i];
            dp[i] = 1;
            for(int j=i-1;j>=0;j--) {
                String pre = words[j];
                if(pre.length()+1==curr.length()) {
                    if(isChain(pre, curr)) {
                        dp[i] = Math.max(dp[i], dp[j]+1);
                    }
                }else if(pre.length()+1<curr.length()) {
                    break;
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    private boolean isChain(String pre, String curr) {
        int idx1 = 0;
        int idx2 = 0;
        boolean hasInsert = false;
        while(idx1<pre.length()&&idx2<curr.length()) {
            if(pre.charAt(idx1)==curr.charAt(idx2)) {
                idx1++;
            }else {
                if(hasInsert) {
                    return false;
                }
                hasInsert = true;
            }
            idx2++;
        }
        return true;
    }

}
