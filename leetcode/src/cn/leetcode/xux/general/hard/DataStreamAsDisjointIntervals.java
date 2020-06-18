package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 352. 将数据流变为多个不相交区间
 * 给定一个非负整数的数据流输入 a1，a2，…，an，…，
 * 将到目前为止看到的数字总结为不相交的区间列表。
 * 例如，假设数据流中的整数为 1，3，7，2，6，…，每次的总结为：
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 *
 * 进阶：
 * 如果有很多合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
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
