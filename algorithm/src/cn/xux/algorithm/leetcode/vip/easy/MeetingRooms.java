package cn.xux.algorithm.leetcode.vip.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252 会议室
 * 题目描述
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
 * 请你判断一个人是否能够参加这里面的全部会议。
 *
 * 示例 1:
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 *
 * 示例 2:
 * 输入: [[7,10],[2,4]]
 * 输出: true
 */
public class MeetingRooms {

    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals==null||intervals.length<2) {
            return true;
        }
        /*Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });*/
        Arrays.sort(intervals, Comparator.comparingInt(o -> o.start));
        for(int i=1;i<intervals.length;i++) {
            if(intervals[i].start<=intervals[i-1].end) {
                return false;
            }
        }
        return true;
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
