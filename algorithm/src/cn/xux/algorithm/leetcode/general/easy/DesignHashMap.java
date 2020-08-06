package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesignHashMap {
    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     */

}

/**
 * Runtime: 131 ms, faster than 7.23% of Java online submissions for Design HashMap.
 * Memory Usage: 55.7 MB, less than 40.54% of Java online submissions for Design HashMap.
 */
class MyHashMap {

    List<Integer> keys = new ArrayList<>();
    List<Integer> values = new ArrayList<>();

    /** Initialize your data structure here. */
    public MyHashMap() {

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = keys.indexOf(new Integer(key));
        if(index==-1) {
            keys.add(key);
            values.add(value);
        }else {
            values.set(index, value);
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = keys.indexOf(new Integer(key));
        return index==-1?-1:values.get(index);
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = keys.indexOf(new Integer(key));
        if(index!=-1) {
            keys.remove(index);
            values.remove(index);
        }
    }
}

/**
 * Runtime: 70 ms, faster than 26.21% of Java online submissions for Design HashMap.
 * Memory Usage: 59.1 MB, less than 14.57% of Java online submissions for Design HashMap.
 */
class MyHashMap1 {

    int[] map = new int[1000001];

    /** Initialize your data structure here. */
    public MyHashMap1() {
        Arrays.fill(map, -1);
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        map[key] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return map[key];
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        map[key] = -1;
    }
}
