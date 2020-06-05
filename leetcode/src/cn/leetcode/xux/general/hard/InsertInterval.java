package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Interval 插入区间
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    public static List<List<Integer>> solution(List<List<Integer>> intervals, List<Integer> interval) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<intervals.size();i++) {
            List<Integer> list = intervals.get(i);
            if(list.get(1)<interval.get(0)) {
                result.add(list);
            }else if(interval.get(1)<list.get(0)) {
                result.add(interval);
                result.addAll(intervals.subList(i, intervals.size()));
                return result;
            }else {
                interval.set(0, Math.min(interval.get(0), list.get(0)));
                interval.set(1, Math.max(interval.get(1), list.get(1)));
            }
        }
        result.add(interval);
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> intervalList = new ArrayList<List<Integer>>();
        List<Integer> interval1 = new ArrayList<Integer>();
        interval1.add(1);
        interval1.add(2);
        intervalList.add(interval1);
        List<Integer> interval2 = new ArrayList<Integer>();
        interval2.add(3);
        interval2.add(5);
        intervalList.add(interval2);
        List<Integer> interval3 = new ArrayList<Integer>();
        interval3.add(6);
        interval3.add(7);
        intervalList.add(interval3);
        List<Integer> interval4 = new ArrayList<Integer>();
        interval4.add(8);
        interval4.add(10);
        intervalList.add(interval4);
        List<Integer> interval5 = new ArrayList<Integer>();
        interval5.add(12);
        interval5.add(16);
        intervalList.add(interval5);

        List<Integer> interval = new ArrayList<Integer>();
        interval.add(4);
        interval.add(9);

        System.out.println(solution(intervalList, interval));
    }

}
