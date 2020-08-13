package cn.xux.algorithm.leetcode.vip.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 527. 单词缩写（Trie树）
 * 给定一个由n个不重复非空字符串组成的数组，你需要按照以下规则为每个单词生成最小的缩写。
 * 初始缩写由起始字母+省略字母的数量+结尾字母组成。
 * 若存在冲突，亦即多于一个单词有同样的缩写，则使用更长的前缀代替首字母，直到从单词到缩写的映射唯一。
 * 换而言之，最终的缩写必须只能映射到一个单词。
 * 若缩写并不比原单词更短，则保留原样。
 *
 * 示例:
 * 输入: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * 输出: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 *
 * 注意:
 * n和每个单词的长度均不超过 400。
 * 每个单词的长度大于 1。
 * 单词只由英文小写字母组成。
 * 返回的答案需要和原数组保持同一顺序。
 */
public class WordAbbreviation {

    public static void main(String[] args) {
        new WordAbbreviation().wordsAbbreviation(new String[]{
                "like", "god", "internal", "me", "internet", "interval",
                "intension", "face", "intrusion"});
    }

    public String[] wordsAbbreviation(String[] dict) {
        if(dict==null||dict.length==0) {
            return dict;
        }
        // key由字符串长度和末尾字符决定
        Map<Integer, Trie> map = new HashMap<>();
        for(String word : dict) {
            if(word==null||word.length()<4) {
                continue;
            }
            int key = word.length()*401+word.charAt(word.length()-1);
            if(!map.containsKey(key)) {
                map.put(key, new Trie());
            }
            insert(map.get(key), word);
        }
        String[] ans = new String[dict.length];
        for(int i=0;i<dict.length;i++) {
            String word = dict[i];
            if(word==null||word.length()<4) {
                ans[i] = word;
            }else {
                int key = word.length()*401+word.charAt(word.length()-1);
                ans[i] = get(map.get(key), word);
            }
        }
        return ans;
    }

    private String get(Trie root, String word) {
        Trie curr = root;
        int len = word.length();
        for(int i=0;i<len-1;i++) {
            int idx = word.charAt(i)-'a';
            curr = curr.children[idx];
            if(curr.cnt==1) {
                String ans = word.substring(0,i+1)+(len-i-2)+word.charAt(len-1);
                return ans.length()<len?ans:word;
            }
        }
        return word;
    }

    private void insert(Trie root, String word) {
        Trie curr = root;
        int len = word.length();
        for(int i=0;i<len-1;i++) {
            int idx = word.charAt(i)-'a';
            if(curr.children[idx]==null) {
                curr.children[idx] = new Trie();
            }
            curr = curr.children[idx];
            curr.cnt++;
        }
    }

    class Trie {
        Trie[] children = new Trie[26];
        int cnt = 0;
    }

}
