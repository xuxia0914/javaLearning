package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 587. 安装栅栏
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。
 * 由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。
 * 你需要找到正好位于栅栏边界上的树的坐标。
 *
 * 示例 1:
 * 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 * 解释:
 *
 * 示例 2:
 * 输入: [[1,2],[2,2],[4,2]]
 * 输出: [[1,2],[2,2],[4,2]]
 * 解释: 即使树都在一条直线上，你也需要先用绳子包围它们。
 *
 * 注意:
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 */
public class ErectTheFence {

    public int[][] outerTrees(int[][] points) {
        Set<int[]> hull = new HashSet<>();

        // 如果树的棵数小于 4 ，那么直接返回
        if (points.length < 4) {
            for (int[] p : points) hull.add(p);
            return hull.toArray(new int[hull.size()][]);
        }

        // 找到最左边的点
        int leftMost = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] < points[leftMost][0]) leftMost = i;
        }

        int p = leftMost;
        do {
            int q = (p + 1) % points.length;

            for (int i = 0; i < points.length; i++) {
                // 如果 i 点在 pq 线下方，则使用 i 点
                if (orientation(points[p], points[i], points[q]) < 0) q = i;
            }

            for (int i = 0; i < points.length; i++) {
                // p、q、i 在同一条线上的情况，并且 i 在 p 和 q 的中间的时候
                // 也需要将这个点算进来
                if (i != p && i != q
                        && orientation(points[p], points[i], points[q]) == 0
                        && inBetween(points[p], points[i], points[q])) {
                    hull.add(points[i]);
                }
            }

            hull.add(points[q]);
            // 重置 p 为 q，接着下一轮的遍历
            p = q;
        } while (p != leftMost);

        return hull.toArray(new int[hull.size()][]);
    }

    // 以下 pq 和 qr 都是向量
    // pq * qr > 0 表示 r 点在 pq 线上方
    // pq * qr < 0 表示 r 点在 pq 线下方
    // pq * qr = 0 表示 p、q、r 一条线
    //           |(q[0]-p[0]) (q[1]-p[1])|
    // pq * qr = |                       | = (q[0]-p[0]) * (r[1]-q[1]) - (r[0]-q[0]) * (q[1]-p[1])
    //           |(r[0]-q[0]) (r[1]-q[1])|
    private int orientation(int[] p, int[] r, int[] q) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (r[0] - q[0]) * (q[1] - p[1]);
    }

    // 判断 r 点是不是在 p 点和 q 点之间，需要考虑以下两种情况：
    // 1. q 点在 p 点的左边或者右边
    // 2. q 点在 p 点的上边或者下边
    private boolean inBetween(int[] p, int[] r, int[] q) {
        boolean a = r[0] >= p[0] && r[0] <= q[0] || r[0] <= p[0] && r[0] >= q[0];
        boolean b = r[1] >= p[1] && r[1] <= q[1] || r[1] <= p[1] && r[1] >= q[1];
        return a && b;
    }

}
