package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253. 会议室 II
 *
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],…] (si < ei)，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 *
 * 示例 1:
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 *
 * 示例 2:
 * 输入: [[7,10],[2,4]]
 * 输出: 1
 */
public class MeetingRoomsII {

    public int minMeetingRooms(Interval[] intervals) {
        if(intervals==null||intervals.length==0) {
            return 0;
        }
        Arrays.sort(intervals, (o1,o2)->o1.start==o2.start?o1.end-o2.end:o1.start-o2.start);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int res = 1;
        queue.offer(intervals[0].end);
        for(Interval interval : intervals) {
            while(!queue.isEmpty()&&queue.peek()<interval.start) {
                queue.poll();
            }
            queue.offer(interval.end);
            res = Math.max(res, queue.size());
        }
        return res;
    }

    /**
     * 分别对起始时间和结束时间排序，由于会议之间并无差异（不像skyline问题，不同建筑的高度不一样），
     * 所以分别使用两个指针来推进起始时间和结束时间
     * O(nlogn)
     */
    public int minMeetingRooms1(Interval[] intervals) {
        if(intervals==null||intervals.length==0) {
            return 0;
        }
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for(int i=0;i<n;i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int idxStart = 0;
        int idxEnd = 0;
        int res = 0;
        while(idxStart<n) {
            if(starts[idxStart]<=ends[idxEnd]) {
                res++;
            }else {
                idxEnd++;
            }
            idxStart++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MeetingRoomsII().minMeetingRooms(new Interval[]{new Interval(0,30), new Interval(5,10), new Interval(15,20)}));
        System.out.println(new MeetingRoomsII().minMeetingRooms(new Interval[]{new Interval(7,10), new Interval(2,4)}));
    }

}

class Interval {
    int start;
    int end;
    Interval() {
        start = 0;
        end = 0;
    }
    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
