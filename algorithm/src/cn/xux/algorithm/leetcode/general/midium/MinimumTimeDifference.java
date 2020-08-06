package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
 * Example 1:
 * Input: ["23:59","00:00"]
 * Output: 1
 * Note:
 * The number of time points in the given list is at least 2 and won't exceed 20000.
 * The input time is legal and ranges from 00:00 to 23:59.
 */
public class MinimumTimeDifference {

    public int findMinDifference(List<String> timePoints) {
        if(timePoints==null||timePoints.size()<2) {
            return 0;
        }
        int size = timePoints.size();
        int[] minutes = new int[size];
        String[] tmp;
        for(int i=0;i<size;i++) {
            tmp = timePoints.get(i).split(":");
            minutes[i] = Integer.valueOf(tmp[0])*60+Integer.valueOf(tmp[1]);
        }
        int res = Integer.MAX_VALUE;
        for(int i=0;i<size;i++) {
            for(int j=i+1;j<size;j++) {
                res = Math.min(res, Math.min(Math.abs(minutes[i]-minutes[j]), 1440-Math.abs(minutes[i]-minutes[j])));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinimumTimeDifference m = new MinimumTimeDifference();
        List<String> timePoints = new ArrayList<>();
        timePoints.add("00:00");
        timePoints.add("23:59");
        timePoints.add("00:00");
        System.out.println(m.findMinDifference(timePoints));
    }

}
