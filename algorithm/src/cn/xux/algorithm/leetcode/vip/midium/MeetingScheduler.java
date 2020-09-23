package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1229：安排会议日程
 * 你是一名行政助理，手里有两位客户的空闲时间表：slots1 和 slots2，
 * 以及会议的预计持续时间 duration，请你为他们安排合适的会议时间。
 * 「会议时间」是两位客户都有空参加，并且持续时间能够满足预计时间 duration 的 最早的时间间隔。
 * 如果没有满足要求的会议时间，就请返回一个 空数组。
 * 「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时间 end 组成，
 * 表示从 start 开始，到 end 结束。
 * 题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，
 * 也就是说，对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，
 * 要么 start1 > end2，要么 start2 > end1。
 *
 * 示例 1：
 * 输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * 输出：[60,68]
 *
 * 示例 2：
 * 输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * 输出：[]
 *
 * 提示：
 * 1 <= slots1.length, slots2.length <= 10^4
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 10^9
 * 1 <= duration <= 10^6
 */
public class MeetingScheduler {

    public int[] minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt(o->o[0]));
        Arrays.sort(slots2, Comparator.comparingInt(o->o[0]));
        int i1 = 0;
        int i2 = 0;
        while(i1<slots1.length&&i2<slots2.length) {
            int min = Math.min(slots1[i1][0], slots2[i2][0]);
            int max = Math.min(slots1[i1][1], slots2[i2][1]);
            if(max-min>=duration) {
                return new int[]{min, min+duration};
            }
            if(slots1[i1][1]>slots1[i2][1]) {
                i2++;
            }else {
                i1++;
            }
        }
        return new int[0];
    }

}
