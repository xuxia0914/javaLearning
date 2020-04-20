package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 792. 匹配子序列的单词数
 * 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。
 *
 * 示例:
 * 输入:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * 输出: 3
 * 解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。
 *
 * 注意:
 * 所有在words和 S 里的单词都只由小写字母组成。
 * S 的长度在 [1, 50000]。
 * words 的长度在 [1, 5000]。
 * words[i]的长度在[1, 50]。
 */
public class NumberOfMatchingSubsequences {

    public int numMatchingSubseq(String S, String[] words) {
        if(S==null||S.length()==0||words==null||words.length==0) {
            return 0;
        }
        Queue<String>[] mem = new Queue[26];
        for(String word : words) {
            if(word!=null||word.length()!=0) {
                int idx = word.charAt(0)-'a';
                if(mem[idx]==null) {
                    mem[idx] = new LinkedList<>();
                }
                mem[idx].offer(word);
            }
        }
        int len = S.length();
        int result = 0;
        for(int i=0;i<len;i++) {
            int idx = S.charAt(i)-'a';
            if(mem[idx]!=null) {
                int size = mem[idx].size();
                while(size-->0) {
                    String curr = mem[idx].poll();
                    if(curr.length()==1) {
                        result++;
                    }else {
                        String tmp = curr.substring(1);
                        int nextIdx = tmp.charAt(0)-'a';
                        if(mem[nextIdx]==null) {
                            mem[nextIdx] = new LinkedList<>();
                        }
                        mem[nextIdx].offer(tmp);
                    }
                }
            }
        }
        return result;
    }

}
