package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 472. 连接词
 * 给定一个不含重复单词的列表，编写一个程序，返回给定单词列表中所有的连接词。
 * 连接词的定义为：一个字符串完全是由至少两个给定数组中的单词组成的。
 *
 * 示例:
 * 输入: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释: "catsdogcats"由"cats", "dog" 和 "cats"组成;
 *      "dogcatsdog"由"dog", "cats"和"dog"组成;
 *      "ratcatdogcat"由"rat", "cat", "dog"和"cat"组成。
 *
 * 说明:
 * 给定数组的元素总数不超过 10000。
 * 给定数组中元素的长度总和不超过 600000。
 * 所有输入字符串只包含小写字母。
 * 不需要考虑答案输出的顺序。
 */
public class ConcatenatedWords {

    /*
     * Trie + DFS
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
            if (dfs(word.toCharArray(), 0, root)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean dfs(char[] str, int start, TrieNode root) {
        int n = str.length;
        TrieNode node = root;
        for (int i = start; i < n; i++) {
            if (node.children[str[i]-'a']==null) {
                return false;
            }
            node = node.children[str[i]-'a'];
            // 短路运算: 如果找到了一个字符串则尝试从头开始走, 找下一个字符串
            if (node.isWord && dfs(str, i + 1, root)) {
                return true;
            }
        }
        // start != 0 含义即是不能匹配到自己
        return node.isWord && start != 0;
    }

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
            if (node.children[c-'a']==null) {
                node.children[c-'a'] = new TrieNode();
            }
            node = node.children[c-'a'];
        }
        node.isWord = true;
    }

}