package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TrieNode;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *
 * For example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 * click to show hint.
 *
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
 */
public class AddAndSearchWordDataStructureDesign {

    TrieNode root;

    /** Initialize your data structure here. */
    public AddAndSearchWordDataStructureDesign() {
        this.root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word==null||word.length()==0) {
            return;
        }
        TrieNode curr = this.root;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if(curr.children[c-'a']==null) {
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
        }
        curr.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word==null||word.length()==0) {
            return false;
        }
        return search(word, this.root);
    }

    public boolean search(String word, TrieNode curr) {
        if(word.length()==0) {
            if(curr.isWord) {
                return true;
            }else {
                return false;
            }

        }
        char c = word.charAt(0);
        if(c=='.') {
            for(TrieNode node : curr.children) {
                if(node!=null&&search(word.substring(1), node)) {
                    return true;
                }
            }
        }else {
            if(curr.children[c-'a']!=null) {
                return search(word.substring(1), curr.children[c-'a']);
            }
        }
        return false;
    }

}
