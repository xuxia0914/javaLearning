package cn.xux.algorithm.common;

public class Trie {
    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word==null||"".equals(word)) {
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

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if(word==null||"".equals(word)) {
            return false;
        }
        TrieNode curr = this.root;
        char c;
        for(int i=0;i<word.length();i++) {
            c = word.charAt(i);
            if(curr.children[c-'a']!=null) {
                curr = curr.children[c-'a'];
            }else {
                return false;
            }
        }
        return curr.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if(prefix==null||"".equals(prefix)) {
            return false;
        }
        TrieNode curr = this.root;
        char c;
        for(int i=0;i<prefix.length();i++) {
            c = prefix.charAt(i);
            if(curr.children[c-'a']!=null) {
                curr = curr.children[c-'a'];
            }else {
                return false;
            }
        }
        return true;
    }

}
