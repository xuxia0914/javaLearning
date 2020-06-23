package cn.leetcode.xux.general.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 432. 全 O(1) 的数据结构
 * 请你实现一个数据结构支持以下操作：
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否则使一个存在的 key 值减一。
 *              如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串"" 。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 *
 * 挑战：
 * 你能够以 O(1) 的时间复杂度实现所有操作吗？
 */
public class AllOoneDataStructure {

    //["AllOne","inc","inc","inc","inc","inc","inc","dec", "dec","getMinKey","dec","getMaxKey","getMinKey"]
    //[[],["a"],["b"],["b"],["c"],["c"],["c"],["b"],["b"],[],["a"],[],[]]
    //[null,null,null,null,null,null,null,null,null,"a",null,"c","c"]
    public static void main(String[] args) {
        AllOne ao = new AllOne();
        ao.inc("a");
        ao.inc("b");
        ao.inc("c");
        ao.inc("c");
        ao.inc("c");
        ao.dec("b");
        ao.dec("b");
        System.out.println(ao.getMinKey());
        ao.dec("a");
        System.out.println(ao.getMaxKey());
        System.out.println(ao.getMinKey());
    }

}

class AllOne {

    Map<String, Integer> map;
    TwoWayListNode head;
    TwoWayListNode tail;
    Map<Integer, TwoWayListNode> valToNode;

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        head = null;
        tail = null;
        valToNode = new HashMap<>();
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int val = map.getOrDefault(key, 0);
        map.put(key, val+1);
        if(valToNode.containsKey(val+1)) {
            valToNode.get(val+1).keySet.add(key);
        }else {
            TwoWayListNode curr = new TwoWayListNode(val+1);
            curr.keySet.add(key);
            valToNode.put(val+1, curr);
            if(val==0) {
                if(head==null) {
                    head = curr;
                    tail = curr;
                }else {
                    curr.next = head;
                    head.pre = curr;
                    head = curr;
                }
            }else {
                TwoWayListNode pre = valToNode.get(val);
                if(pre==tail) {
                    pre.next = curr;
                    curr.pre = pre;
                    tail = curr;
                }else {
                    curr.next = pre.next;
                    curr.next.pre = curr;
                    curr.pre = pre;
                    pre.next = curr;
                }
            }
        }
        if(val==0) {
            return;
        }
        remove(val, key);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(!map.containsKey(key)) {
            return;
        }
        int val = map.get(key);
        if(val==1) {
            map.remove(key);
        }else {
            map.put(key, val-1);
        }
        if(valToNode.containsKey(val-1)) {
            valToNode.get(val-1).keySet.add(key);
        }else if(val>1) {
            TwoWayListNode curr = valToNode.get(val);
            TwoWayListNode pre = new TwoWayListNode(val-1);
            valToNode.put(val-1, pre);
            pre.keySet.add(key);
            if(curr==head) {
                curr.pre = pre;
                pre.next = curr;
                head = pre;
            }else {
                curr.pre.next = pre;
                pre.pre = curr.pre;
                pre.next = curr;
                curr.pre = pre;
            }
        }
        remove(val, key);
    }

    private void remove(int val, String key) {
        TwoWayListNode curr = valToNode.get(val);
        if(curr.keySet.size()>1) {
            curr.keySet.remove(key);
        }else {
            valToNode.remove(val);
            if(curr==head) {
                head = head.next;
                if(head!=null) {
                    head.pre = null;
                }
            }else if(curr==tail) {
                tail.pre.next = null;
                tail = tail.pre;
            }else {
                curr.pre.next = curr.next;
                curr.next.pre = curr.pre;
            }
        }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(tail!=null) {
            return tail.keySet.iterator().next();
        }else {
            return "";
        }
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(head!=null) {
            return head.keySet.iterator().next();
        }else {
            return "";
        }
    }

}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

//双向链表
class TwoWayListNode {

    int val;
    Set<String> keySet;
    TwoWayListNode pre;
    TwoWayListNode next;

    TwoWayListNode(int val) {
        this.val = val;
        keySet = new HashSet<>();
    }

}
