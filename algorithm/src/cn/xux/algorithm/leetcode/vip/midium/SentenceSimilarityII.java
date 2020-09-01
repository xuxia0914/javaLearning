package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 737. 句子相似性 II
 * 给定两个句子 words1, words2 （每个用字符串数组表示），
 * 和一个相似单词对的列表 pairs ，判断是否两个句子是相似的。
 *
 * 例如，当相似单词对是 pairs = [["great", "fine"], ["acting","drama"],
 * ["skills","talent"]]的时候，words1 = ["great", "acting", "skills"]
 * 和 words2 = ["fine", "drama", "talent"] 是相似的。
 *
 * 注意相似关系是 具有 传递性的。
 * 例如，如果 “great” 和 “fine” 是相似的，“fine” 和 “good” 是相似的，
 * 则 “great” 和 “good” 是相似的。
 *
 * 而且，相似关系是具有对称性的。
 * 例如，“great” 和 “fine” 是相似的相当于 “fine” 和 “great” 是相似的。
 *
 * 并且，一个单词总是与其自身相似。
 * 例如，句子 words1 = [“great”], words2 = [“great”], pairs = [] 是相似的，尽管没有输入特定的相似单词对。
 *
 * 最后，句子只会在具有相同单词个数的前提下才会相似。
 * 所以一个句子 words1 = [“great”] 永远不可能和句子 words2 = [“doubleplus”,“good”] 相似。
 *
 * 注：
 * words1 and words2 的长度不会超过 1000。
 * pairs 的长度不会超过 2000。
 * 每个pairs[i] 的长度为 2。
 * 每个 words[i] 和 pairs[i][j] 的长度范围为 [1, 20]。
 */
public class SentenceSimilarityII {

    public boolean areSentencesSimilarTwo(String[] words1,
            String[] words2, String[][] pairs) {
        int len1 = words1.length;
        int len2 = words2.length;
        if(len1!=len2) {
            return false;
        }
        int cnt = 0;
        Map<String, Integer> map = new HashMap<>();
        for(String[] pair : pairs) {
            if(!map.containsKey(pair[0])) {
                map.put(pair[0], cnt++);
            }
            if(!map.containsKey(pair[1])) {
                map.put(pair[1], cnt++);
            }
        }
        DSU dsu = new DSU(cnt);
        for(String[] pair : pairs) {
            dsu.union(map.get(pair[0]), map.get(pair[1]));
        }
        for(int i=0;i<len1;i++) {
            if(words1[i].equals(words2[i])) {
                continue;
            }
            int idx1 = map.getOrDefault(words1[i], -1);
            int idx2 = map.getOrDefault(words2[i], -1);
            if(idx1==-1||idx2==-1&&dsu.find(idx1)!=dsu.find(idx2)) {
                return false;
            }
        }
        return true;
    }

    class DSU {

        int[] parent;

        public DSU(int n) {
            parent = new int[n];
            for(int i=1;i<n;i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if(parent[x]!=x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(y)] = find(x);
        }
    }


}
