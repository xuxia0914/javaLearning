package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 352. 将数据流变为多个不相交区间
 *  给你一个由非负整数 a1, a2, ..., an 组成的数据流输入，
 *  请你将到目前为止看到的数字总结为不相交的区间列表。
 *
 * 实现 SummaryRanges 类：
 *
 * SummaryRanges() 使用一个空数据流初始化对象。
 * void addNum(int val) 向数据流中加入整数 val 。
 * int[][] getIntervals() 以不相交区间 [starti, endi] 的列表形式返回对数据流中整数的总结。
 *
 *
 * 示例：
 *
 * 输入：
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * 输出：
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 *
 * 解释：
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // 返回 [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // 返回 [[1, 3], [6, 7]]
 *
 *
 * 提示：
 *
 * 0 <= val <= 104
 * 最多调用 addNum 和 getIntervals 方法 3 * 104 次
 *
 *
 * 进阶：如果存在大量合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 */
public class DataStreamAsDisjointIntervals {

    //["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]
    //[[],[1],[],[3],[],[7],[],[2],[],[6],[]]
    public static void main(String[] args) {
        SummaryRanges sr = new SummaryRanges();
        sr.addNum(1);
        System.out.println(sr.getIntervals());
        sr.addNum(3);
        System.out.println(sr.getIntervals());
        sr.addNum(7);
        System.out.println(sr.getIntervals());
        sr.addNum(2);
        System.out.println(sr.getIntervals());
        sr.addNum(6);
        System.out.println(sr.getIntervals());
    }

}

class SummaryRanges {

    List<int[]> list;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        list = new ArrayList<>();
    }

    public void addNum(int val) {
        int size = list.size();
        if(size==0) {
            list.add(new int[]{val, val});
        }
        //找到list中左边界小于等于val的最后一个区间
        int left = 0;
        int right = size-1;
        while(left<right) {
            int mid = (left+right+1)/2;
            if(list.get(mid)[0]<=val) {
                left = mid;
            }else {
                right = mid-1;
            }
        }
        //边界情况
        int[] leftInterval = list.get(left);
        if(val>=leftInterval[0]&&val<=leftInterval[1]) {
            return;
        }
        if(left==0&&leftInterval[0]>val) {
            if(val==leftInterval[0]-1) {
                leftInterval[0]--;
            }else {
                list.add(0, new int[]{val, val});
            }
            return;
        }
        if(left==size-1) {
            if(leftInterval[1]+1==val) {
                leftInterval[1]++;
            }else {
                list.add(new int[]{val, val});
            }
            return;
        }
        int[] rightInterval = list.get(left+1);
        if(leftInterval[1]+2==rightInterval[0]) {
            list.remove(left+1);
            list.remove(left);
            list.add(left, new int[]{leftInterval[0], rightInterval[1]});
        }else if (leftInterval[1]+1==val) {
            leftInterval[1]++;
        }else if(val+1==rightInterval[0]) {
            rightInterval[0]--;
        }else {
            list.add(left+1, new int[]{val, val});
        }
    }

    public int[][] getIntervals() {
        int size = list.size();
        int[][] ans = new int[size][2];
        for(int i=0;i<size;i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
