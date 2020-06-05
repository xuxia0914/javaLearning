package cn.leetcode.xux.general.midium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II（会议室）
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 *
 * example 1:
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 *
 * Example 2:
 * Input: [[7,10],[2,4]]
 * Output: 1
 *
 */
public class MeetingRoomsII {

    /**
     * 对intervals排序；
     * 遍历并用小顶堆记录会议结束时间，判断每个会议开始时间是否大于堆顶时间，不是则会议室+1；是则移除堆顶
     * O(nlogn)
     */
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals==null||intervals.length==0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start!=o2.start) {
                    return o1.start-o2.start;
                }else {
                    return o1.end-o2.end;
                }
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int res = 0;
        for(Interval interval : intervals) {
            queue.offer(interval.end);
            if(queue.peek()>=interval.start) {
                res++;
            }else {
                queue.poll();
            }
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
                idxStart++;
            }else {
                idxStart++;
                idxEnd++;
            }
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
