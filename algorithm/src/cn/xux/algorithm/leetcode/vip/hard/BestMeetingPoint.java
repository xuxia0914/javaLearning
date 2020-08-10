package cn.xux.algorithm.leetcode.vip.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 296. 最佳的碰头地点
 * 有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。
 * 给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。
 * 1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，
 * 其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。
 *
 * 示例：
 * 输入:
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 输出: 6
 * 解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
 *      (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
 */
public class BestMeetingPoint {

    public int minTotalDistance(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    x.add(i);
                    y.add(j);
                }
            }
        }
        Collections.sort(x);
        int ans = 0;
        int size = x.size();
        for(int i=0;i<size/2;i++) {
            ans += x.get(size-1-i)-x.get(i);
            ans += y.get(size-1-i)-y.get(i);
        }
        return ans;
    }

}
