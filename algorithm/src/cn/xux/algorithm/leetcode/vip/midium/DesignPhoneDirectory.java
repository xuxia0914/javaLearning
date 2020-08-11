package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 379. 电话目录管理系统（哈希set）
 * 设计一个电话目录管理系统，让它支持以下功能：
 * get: 分配给用户一个未被使用的电话号码，获取失败请返回 -1
 * check: 检查指定的电话号码是否被使用
 * release: 释放掉一个电话号码，使其能够重新被分配
 * 示例：
 * // 初始化电话目录，包括 3 个电话号码：0，1 和 2。
 * PhoneDirectory directory = new PhoneDirectory(3);
 * // 可以返回任意未分配的号码，这里我们假设它返回 0。
 * directory.get();
 * // 假设，函数返回 1。
 * directory.get();
 * // 号码 2 未分配，所以返回为 true。
 * directory.check(2);
 * // 返回 2，分配后，只剩一个号码未被分配。
 * directory.get();
 * // 此时，号码 2 已经被分配，所以返回 false。
 * directory.check(2);
 * // 释放号码 2，将该号码变回未分配状态。
 * directory.release(2);
 * // 号码 2 现在是未分配状态，所以返回 true。
 * directory.check(2);
 *
 * 提示：
 * 1 <= maxNumbers <= 10^4
 * 0 <= number < maxNumbers
 * 调用方法的总数处于区间 [0 - 20000] 之内
 */
public class DesignPhoneDirectory {
}

class PhoneDirectory {

    Queue<Integer> unUsed;
    Set<Integer> used;
    int n;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        n = maxNumbers;
        unUsed = new LinkedList<>();
        for(int i=0;i<maxNumbers;i++) {
            unUsed.offer(i);
        }
        used = new HashSet<>();
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(unUsed.isEmpty()) {
            return -1;
        }
        int ans = unUsed.poll();
        used.add(ans);
        return ans;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(number<n&&used.contains(number)) {
            used.remove(number);
            unUsed.offer(number);
        }
    }

}
