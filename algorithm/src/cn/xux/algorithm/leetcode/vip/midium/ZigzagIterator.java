package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Iterator;
import java.util.List;

/**
 * 281. 锯齿迭代器
 * 给出两个一维的向量，请你实现一个迭代器，交替返回它们中的元素。
 *
 * 示例:
 * 输入:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * 输出:
 * [1,3,2,4,5,6]
 *
 * 解析: 通过连续调用 next 函数直到 hasNext 函数返回 false，
 * next 函数返回值的次序应依次为: [1,3,2,4,5,6]。
 *
 * 拓展：假如给你 k 个一维向量呢？你的代码在这种情况下的扩展性又会如何呢?
 */
public class ZigzagIterator {

    Iterator<Integer> cur_iterator;
    Iterator<Integer> iterator1;
    Iterator<Integer> iterator2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.iterator1 = v1.iterator();
        this.iterator2 = v2.iterator();
        this.cur_iterator = (this.iterator1.hasNext() ? this.iterator1 : this.iterator2);
    }

    public int next() {
        int ret = cur_iterator.next();
        if (cur_iterator == iterator1) {
            if (iterator2.hasNext()) {
                cur_iterator = iterator2;
            }
        } else{
            if (iterator1.hasNext()) {
                cur_iterator = iterator1;
            }
        }
        return ret;
    }

    public boolean hasNext() {
        return cur_iterator.hasNext();
    }

}
