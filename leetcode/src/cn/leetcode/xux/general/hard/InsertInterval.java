package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 *
 * 示例 2:
 * 输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出: [[1,2],[3,10],[12,16]]
 * 解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals==null||intervals.length==0) {
            return new int[][]{newInterval};
        }
        int n = intervals.length;
        List<int[]> list = new ArrayList<>();
        int idx = 0;
        while(idx<n&&intervals[idx][1]<newInterval[0]) {
            list.add(intervals[idx++]);
        }
        if(idx==n) {
            list.add(newInterval);
        }else {
            int left = Math.min(newInterval[0], intervals[idx][0]);
            int right = newInterval[1];
            while(idx<n&&intervals[idx][0]<=newInterval[1]) {
                right = Math.max(intervals[idx][1], newInterval[1]);
                idx++;
            }
            list.add(new int[]{left, right});
        }
        while(idx<n) {
            list.add(intervals[idx++]);
        }
        int[][] ans = new int[list.size()][2];
        for(int i=0;i<ans.length;i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

}
