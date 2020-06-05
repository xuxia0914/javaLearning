package cn.leetcode.xux.general.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 170 Two Sum III - Data structure design 两数之和之三 - 数据结构设计
 * Design and implement a TwoSum class. It should support the following operations:add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 */
public class TwoSumIII {

    Map<Integer, Integer> tracker = new HashMap<>();
    // Add the number to an internal data structure.
    public void add(int number) {
        tracker.put(number, tracker.getOrDefault(number, 0)+1);
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (Integer curr : tracker.keySet()) {
            int target = value - curr;
            if (tracker.containsKey(target)) {
                if (target != curr) {
                    return true;
                } else if (tracker.get(curr) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

}

// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
