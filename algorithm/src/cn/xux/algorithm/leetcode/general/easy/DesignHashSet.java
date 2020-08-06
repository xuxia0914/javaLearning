package cn.xux.algorithm.leetcode.general.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a HashSet without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * add(value): Insert a value into the HashSet.
 * contains(value) : Return whether the value exists in the HashSet or not.
 * remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.
 * Example:
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // returns true
 * hashSet.contains(3);    // returns false (not found)
 * hashSet.add(2);
 * hashSet.contains(2);    // returns true
 * hashSet.remove(2);
 * hashSet.contains(2);    // returns false (already removed)
 * Note:
 * All values will be in the range of [0, 1000000].
 * The number of operations will be in the range of [1, 10000].
 * Please do not use the built-in HashSet library.
 */
public class DesignHashSet {
    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */

}

/**
 * Runtime: 61 ms, faster than 49.33% of Java online submissions for Design HashSet.
 * Memory Usage: 55.9 MB, less than 54.26% of Java online submissions for Design HashSet.
 */
class MyHashSet {

    Map<Integer, Object> map = new HashMap<>();

    /** Initialize your data structure here. */
    public MyHashSet() {}

    public void add(int key) {
        map.put(key, null);
    }

    public void remove(int key) {
        map.remove(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return map.containsKey(key);
    }

}

/**
 * Runtime: 61 ms, faster than 49.33% of Java online submissions for Design HashSet.
 * Memory Usage: 59.2 MB, less than 23.77% of Java online submissions for Design HashSet.
 */
class MyHashSet1 {

    int[] set = new int[1000001];

    /** Initialize your data structure here. */
    public MyHashSet1() {

    }

    public void add(int key) {
        set[key] = 1;
    }

    public void remove(int key) {
        set[key] = 0;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return set[key]==1;
    }
}
