package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 539. 最小时间差
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，
 * 找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 *
 *
 * 示例 1：
 *
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 *
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *
 *
 * 提示：
 *
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
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
            minutes[i] = Integer.parseInt(tmp[0])*60+Integer.parseInt(tmp[1]);
        }
        Arrays.sort(minutes);
        int res = 1440+minutes[0]-minutes[size-1];
        for(int i=0;i<size-1;i++) {
            res = Math.min(res, minutes[i+1]-minutes[i]);
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
