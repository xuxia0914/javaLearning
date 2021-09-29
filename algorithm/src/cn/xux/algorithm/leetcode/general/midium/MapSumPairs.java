package cn.xux.algorithm.leetcode.general.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 677. 键值映射
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 *
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。
 *      如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *
 *
 * 示例：
 *
 * 输入：
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 *
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 *
 * 提示：
 *
 * 1 <= key.length, prefix.length <= 50
 * key 和 prefix 仅由小写英文字母组成
 * 1 <= val <= 1000
 * 最多调用 50 次 insert 和 sum
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
            res += curr.val;
            for(Trie child : curr.children) {
                if(child!=null) {
                    queue.offer(child);
                }
            }
        }
        return res;
    }

    class Trie {

        Trie[] children = new Trie[26];
        int val;

    }

}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
