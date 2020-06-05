package cn.leetcode.xux.general.midium;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 380. 常数时间插入、删除和获取随机元素
 * 设计一个支持在平均时间复杂度 O(1) 下，执行以下操作的数据结构。
 *
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 * 示例 :
 *
 * // 初始化一个空的集合。
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomSet.insert(1);
 *
 * // 返回 false ，表示集合中不存在 2 。
 * randomSet.remove(2);
 *
 * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomSet.insert(2);
 *
 * // getRandom 应随机返回 1 或 2 。
 * randomSet.getRandom();
 *
 * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomSet.remove(1);
 *
 * // 2 已在集合中，所以返回 false 。
 * randomSet.insert(2);
 *
 * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * randomSet.getRandom();
 */
public class InsertDeleteGetrandomO1 {

    public static void main(String[] args) {
        // 初始化一个空的集合。
        RandomizedSet randomSet = new RandomizedSet();

        // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        System.out.println(randomSet.insert(1));

        // 返回 false ，表示集合中不存在 2 。
        System.out.println(randomSet.remove(2));

        // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        System.out.println(randomSet.insert(2));

        // getRandom 应随机返回 1 或 2 。
        System.out.println(randomSet.getRandom());

        // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        System.out.println(randomSet.remove(1));

        // 2 已在集合中，所以返回 false 。
        System.out.println(randomSet.insert(2));

        // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
        System.out.println(randomSet.getRandom());
    }

}

class RandomizedSet {

    Map<Integer, Integer> idxVal = new HashMap<>();
    Map<Integer, Integer> valIdx = new HashMap<>();
    int size = 0;
    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(this.valIdx.containsKey(val)) {
            return false;
        }
        this.idxVal.put(this.size, val);
        this.valIdx.put(val, this.size);
        this.size++;
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!this.valIdx.containsKey(val)) {
            return false;
        }
        int idx = this.valIdx.get(val);
        this.valIdx.remove(val);
        this.idxVal.remove(idx);
        //把最后插入的值移到被删除的位置
        if(idx!=this.size-1) {
            int lastVal = this.idxVal.get(this.size-1);
            this.idxVal.remove(this.size-1);
            this.valIdx.remove(lastVal);
            this.idxVal.put(idx, lastVal);
            this.valIdx.put(lastVal, idx);
        }
        this.size--;
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int idx = this.random.nextInt(this.size);
        return this.idxVal.get(idx);
    }
    
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
