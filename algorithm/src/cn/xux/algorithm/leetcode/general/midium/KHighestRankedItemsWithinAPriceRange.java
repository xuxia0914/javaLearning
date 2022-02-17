package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 2146. 价格范围内最高排名的 K 样物品
 * 给你一个下标从 0 开始的二维整数数组 grid ，
 * 它的大小为 m x n ，表示一个商店中物品的分布图。数组中的整数含义为：
 * <p>
 * 0 表示无法穿越的一堵墙。
 * 1 表示可以自由通过的一个空格子。
 * 所有其他正整数表示该格子内的一样物品的价格。你可以自由经过这些格子。
 * 从一个格子走到上下左右相邻格子花费 1 步。
 * <p>
 * 同时给你一个整数数组 pricing 和 start ，
 * 其中 pricing = [low, high] 且 start = [row, col] ，
 * 表示你开始位置为 (row, col) ，
 * 同时你只对物品价格在 闭区间 [low, high] 之内的物品感兴趣。
 * 同时给你一个整数 k 。
 * <p>
 * 你想知道给定范围 内 且 排名最高 的 k 件物品的 位置 。
 * 排名按照优先级从高到低的以下规则制定：
 * <p>
 * 距离：定义为从 start 到一件物品的最短路径需要的步数（较近 距离的排名更高）。
 * 价格：较低 价格的物品有更高优先级，但只考虑在给定范围之内的价格。
 * 行坐标：较小 行坐标的有更高优先级。
 * 列坐标：较小 列坐标的有更高优先级。
 * 请你返回给定价格内排名最高的 k 件物品的坐标，
 * 将它们按照排名排序后返回。如果给定价格内少于 k 件物品，
 * 那么请将它们的坐标 全部 返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]],
 * pricing = [2,5], start = [0,0], k = 3
 * 输出：[[0,1],[1,1],[2,1]]
 * 解释：起点为 (0,0) 。
 * 价格范围为 [2,5] ，我们可以选择的物品坐标为 (0,1)，(1,1)，(2,1) 和 (2,2) 。
 * 这些物品的排名为：
 * - (0,1) 距离为 1
 * - (1,1) 距离为 2
 * - (2,1) 距离为 3
 * - (2,2) 距离为 4
 * 所以，给定价格范围内排名最高的 3 件物品的坐标为 (0,1)，(1,1) 和 (2,1) 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]],
 * pricing = [2,3], start = [2,3], k = 2
 * 输出：[[2,1],[1,2]]
 * 解释：起点为 (2,3) 。
 * 价格范围为 [2,3] ，我们可以选择的物品坐标为 (0,1)，(1,1)，(1,2) 和 (2,1) 。
 * 这些物品的排名为：
 * - (2,1) 距离为 2 ，价格为 2
 * - (1,2) 距离为 2 ，价格为 3
 * - (1,1) 距离为 3
 * - (0,1) 距离为 4
 * 所以，给定价格范围内排名最高的 2 件物品的坐标为 (2,1) 和 (1,2) 。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[1,1,1],[0,0,1],[2,3,4]],
 * pricing = [2,3], start = [0,0], k = 3
 * 输出：[[2,1],[2,0]]
 * 解释：起点为 (0,0) 。
 * 价格范围为 [2,3] ，我们可以选择的物品坐标为 (2,0) 和 (2,1) 。
 * 这些物品的排名为：
 * - (2,1) 距离为 5
 * - (2,0) 距离为 6
 * 所以，给定价格范围内排名最高的 2 件物品的坐标为 (2,1) 和 (2,0) 。
 * 注意，k = 3 但给定价格范围内只有 2 件物品。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 0 <= grid[i][j] <= 105
 * pricing.length == 2
 * 2 <= low <= high <= 105
 * start.length == 2
 * 0 <= row <= m - 1
 * 0 <= col <= n - 1
 * grid[row][col] > 0
 * 1 <= k <= m * n
 */
public class KHighestRankedItemsWithinAPriceRange {

    public static void main(String[] args) {
        // [[1,2,0,1],[1,3,0,1],[0,2,5,1]]
        //[2,5]
        //[0,0]
        //3
        System.out.println(new KHighestRankedItemsWithinAPriceRange().highestRankedKItems(
                new int[][]{{1, 2, 0, 1}, {1, 3, 0, 1}, {0, 2, 5, 1}},
                new int[]{2, 5},
                new int[]{0, 0},
                3
        ));
    }

    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dis = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = -1;
            }
        }
        dis[start[0]][start[1]] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        int level = 1;
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] curr = queue.poll();
                for (int[] d : dir) {
                    int[] next = new int[]{curr[0] + d[0], curr[1] + d[1]};
                    if (next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n
                            && grid[next[0]][next[1]] != 0 && dis[next[0]][next[1]] == -1) {
                        dis[next[0]][next[1]] = level;
                        queue.offer(next);
                    }
                }
            }
            level++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (dis[o1[0]][o1[1]] != dis[o2[0]][o2[1]]) {
                return dis[o2[0]][o2[1]] - dis[o1[0]][o1[1]];
            } else if (grid[o1[0]][o1[1]] != grid[o2[0]][o2[1]]) {
                return grid[o2[0]][o2[1]] - grid[o1[0]][o1[1]];
            } else if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            } else if (o1[1] != o2[1]) {
                return o2[1] - o1[1];
            }
            return 0;
        });
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] >= pricing[0] && grid[i][j] <= pricing[1]
                        && dis[i][j] != -1) {
                    pq.offer(new int[]{i, j});
                    if (pq.size() > k) {
                        pq.poll();
                    }
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (pq.size() > 0) {
            int[] pos = pq.poll();
            ans.add(0, Arrays.asList(pos[0], pos[1]));
        }
        return ans;
    }

}
