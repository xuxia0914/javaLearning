package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 381. O(1) 时间插入、删除和获取随机元素 - 允许重复
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * 注意: 允许出现重复元素。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 * 示例:
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 */
public class InsertDeleteGetrandomO1DuplicatesAllowed {



}

class RandomizedCollection {

    ArrayList<Integer> lst;
    HashMap<Integer, Set<Integer>> idx;
    java.util.Random rand = new java.util.Random();

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        lst = new ArrayList<Integer>();
        idx = new HashMap<Integer, Set<Integer>>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!idx.containsKey(val)) idx.put(val, new LinkedHashSet<Integer>());
        idx.get(val).add(lst.size());
        lst.add(val);
        return idx.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!idx.containsKey(val) || idx.get(val).size() == 0) return false;
        int remove_idx = idx.get(val).iterator().next();
        idx.get(val).remove(remove_idx);
        int last = lst.get(lst.size() - 1);
        lst.set(remove_idx, last);
        idx.get(last).add(remove_idx);
        idx.get(last).remove(lst.size() - 1);
        lst.remove(lst.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return lst.get(rand.nextInt(lst.size()));
    }

}