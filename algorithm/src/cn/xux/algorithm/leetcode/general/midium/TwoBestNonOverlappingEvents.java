package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2054. 两个最好的不重叠活动
 * 给你一个下标从 0 开始的二维整数数组 events ，
 * 其中 events[i] = [startTimei, endTimei, valuei] 。
 * 第 i 个活动开始于 startTimei ，结束于 endTimei ，
 * 如果你参加这个活动，那么你可以得到价值 valuei 。
 * 你 最多 可以参加 两个时间不重叠 活动，使得它们的价值之和 最大 。
 * <p>
 * 请你返回价值之和的 最大值 。
 * <p>
 * 注意，活动的开始时间和结束时间是 包括 在活动时间内的，
 * 也就是说，你不能参加两个活动且它们之一的开始时间等于另一个活动的结束时间。
 * 更具体的，如果你参加一个活动，且结束时间为 t ，
 * 那么下一个活动必须在 t + 1 或之后的时间开始。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：events = [[1,3,2],[4,5,2],[2,4,3]]
 * 输出：4
 * 解释：选择绿色的活动 0 和 1 ，价值之和为 2 + 2 = 4 。
 * 示例 2：
 * <p>
 * Example 1 Diagram
 * <p>
 * 输入：events = [[1,3,2],[4,5,2],[1,5,5]]
 * 输出：5
 * 解释：选择活动 2 ，价值和为 5 。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：events = [[1,5,3],[1,5,1],[6,6,5]]
 * 输出：8
 * 解释：选择活动 0 和 2 ，价值之和为 3 + 5 = 8 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= events.length <= 105
 * events[i].length == 3
 * 1 <= startTimei <= endTimei <= 109
 * 1 <= valuei <= 106
 */
public class TwoBestNonOverlappingEvents {

    public static void main(String[] args) {
        System.out.println(new TwoBestNonOverlappingEvents().maxTwoEvents(
                new int[][]{{1, 5, 3}, {1, 5, 1}, {6, 6, 5}}
        ));
    }

    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        // 根据结束时间排序
        Arrays.sort(events, Comparator.comparingInt(e -> e[1]));
        // f[i] 表示 0 ~ i 范围内选一个的最大价值
        int[] f = new int[n];
        // g[i] 表示 0 ~ i 范围内最多选两个的最大价值
        // int[] g = new int[n];
        f[0] = events[0][2];
        // g[0] = events[0][2];
        int g = events[0][2];
        for (int i = 1; i < n; i++) {
            int left = 0;
            int right = i - 1;
            // 二分查找 使得events[x][1]<events[i][0]的最大x
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if (events[mid][1] < events[i][0]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            f[i] = Math.max(f[i - 1], events[i][2]);
            g = Math.max(g, events[i][2] +
                    (events[left][1] >= events[i][0] ? 0 : f[left]));
        }
        return g;
    }

}
