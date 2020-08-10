package cn.xux.algorithm.leetcode.vip.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 346. 数据流中的移动平均值
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 *
 * 示例:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageFromDataStream {

    int size;
    Queue<Integer> queue;
    int sum;

    /** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size) {
        this.size = size;
        queue = new LinkedList<>();
        sum = 0;
    }

    public double next(int val) {
        queue.offer(val);
        sum += val;
        if(queue.size()>size) {
            sum -= queue.poll();
        }
        return  (double)sum/queue.size();
    }

}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */