package cn.leetcode.xux.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * Example:
 * LRUCache cache = new LRUCache( 2 / capacity / );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class LRUCache {

    public List<Integer> keys = new ArrayList<Integer>();
    public List<Integer> values = new ArrayList<Integer>();
    public int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(Integer key, Integer value) {
        if(this.keys.contains(key)) {
            int index = this.keys.indexOf(key);
            this.values.remove(index);
            this.values.add(value);
            this.keys.remove(index);
            this.keys.add(key);
        }else {
            if(this.keys.size()==this.capacity) {
                this.keys.remove(0);
                this.values.remove(0);
            }
            this.keys.add(key);
            this.values.add(value);
        }
    }

    public Integer get(Integer key) {
        if(this.keys.contains(key)) {
            int index = this.keys.indexOf(key);
            Integer value = this.values.get(index);
            this.values.remove(index);
            this.values.add(value);
            this.keys.remove(index);
            this.keys.add(key);
            return value;
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));
        cache.put(3,3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }


}
