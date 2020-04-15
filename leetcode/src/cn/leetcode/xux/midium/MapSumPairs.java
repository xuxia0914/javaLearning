package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 677. 键值映射
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。
 * 如果键已经存在，那么原来的键值对将被替代成新的键值对。
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 *
 * 示例 1:
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 */
public class MapSumPairs {



}

class MapSum {

    Trie trie;

    /** Initialize your data structure here. */
    public MapSum() {
        trie = new Trie();
    }

    public void insert(String key, int val) {
        if(key==null||key.length()==0) {
            return;
        }
        Trie curr = trie;
        for(char c : key.toCharArray()) {
            if(curr.children[c-'a']==null) {
                curr.children[c-'a'] = new Trie();
            }
            curr = curr.children[c-'a'];
        }
        curr.isWord = true;
        curr.val = val;
    }

    public int sum(String prefix) {
        if(prefix==null||prefix.length()==0) {
            return 0;
        }
        Trie curr = trie;
        for(char c : prefix.toCharArray()) {
            if(curr.children[c-'a']==null) {
                return 0;
            }
            curr = curr.children[c-'a'];
        }
        int res = 0;
        Queue<Trie> queue = new LinkedList<>();
        queue.offer(curr);
        while(!queue.isEmpty()) {
            curr = queue.poll();
            if(curr.isWord) {
                res += curr.val;
            }
            for(Trie child : curr.children) {
                if(child!=null) {
                    queue.offer(child);
                }
            }
        }
        return res;
    }
}

class Trie {

    Trie[] children = new Trie[26];
    int val;
    boolean isWord = false;

}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
