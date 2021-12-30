package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 472. 连接词
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * <p>
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释："catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
 * "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
 * "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
 * 示例 2：
 * <p>
 * 输入：words = ["cat","dog","catdog"]
 * 输出：["catdog"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 104
 * 0 <= words[i].length <= 1000
 * words[i] 仅由小写字母组成
 * 0 <= sum(words[i].length) <= 105
 */
public class ConcatenatedWords {

    /*
     * Trie + 记忆化DFS
     * 首先把所有的字符串添加到 Trie 中
     * 然后依次判断每个字符串是否属于答案集合
     * 在前缀树中沿着当前字符串往下走, 碰到一个 isWord 节点时有两个选择: 继续往下走或者从头重新走
     * 如果最终能够恰好走到 isWord 节点, 那么说明当前字符串属于答案集合
     * 为了避免当前字符串自身也在 Trie 中导致一定能够走到 isWord 节点, 可以在判断之前先将该单词删除 (出于效率, 没有必要删除边, 只需要清除标记)
     * 或者加一些额外的判断
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TrieNode root = new TrieNode();
        // 题目描述有问题, 应该补充上连接词至少由两个非空单词组成,
        // 否则如果 words 包含空串, 那么 words 中的其它所有字符串都是连接词. 这里加上特判就可以了.
        for (String word : words) {
            if (!word.equals("")) {
                root.add(word);
            }
        }
        List<String> res = new ArrayList<>();
        for (String word : words) {
            mem = new HashMap<>();
            if (dfs(word, 0, root)) {
                res.add(word);
            }
        }
        return res;
    }

    Map<Integer, Boolean> mem;

    private boolean dfs(String str, int start, TrieNode root) {
        if (mem.containsKey(start)) {
            return mem.get(start);
        }
        int n = str.length();
        TrieNode node = root;
        for (int i = start; i < n; i++) {
            if (node.children[str.charAt(i) - 'a'] == null) {
                mem.put(start, false);
                return false;
            }
            node = node.children[str.charAt(i) - 'a'];
            // 短路运算: 如果找到了一个字符串则尝试从头开始走, 找下一个字符串
            if (node.isWord && dfs(str, i + 1, root)) {
                mem.put(start, true);
                return true;
            }
        }
        boolean ans = node.isWord && start != 0;
        mem.put(start, ans);
        // start != 0 含义即是不能匹配到自己
        return ans;
    }

    class TrieNode {

        public boolean isWord;
        public TrieNode[] children;

        public TrieNode() {
            isWord = false;
            children = new TrieNode[26];
        }

        public void add(String str) {
            TrieNode node = this;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.isWord = true;
        }

    }

}