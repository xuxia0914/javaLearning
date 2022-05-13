package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。
 * “太平洋” 处于大陆的左边界和上边界，
 * 而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个由若干方形单元格组成的网格。
 * 给定一个 m x n 的整数矩阵 heights ，
 * heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，
 * 雨水可以直接向北、南、东、西流向相邻单元格。
 * 水可以从海洋附近的任何单元格流入海洋。
 * <p>
 * 返回 网格坐标 result 的 2D列表 ，
 * 其中 result[i] = [ri, ci] 表示雨水可以
 * 从单元格 (ri, ci) 流向 太平洋和大西洋 。
 * <p>
 * 示例 1：
 * <p>
 * 输入: heights = [
 * [1,2,2,3,5],
 * [3,2,3,4,4],
 * [2,4,5,3,1],
 * [6,7,1,4,5],
 * [5,1,1,2,4]]
 * *   Pacific ~   ~   ~   ~   ~
 * *        ~  1   2   2   3  (5) *
 * *        ~  3   2   3  (4) (4) *
 * *        ~  2   4  (5)  3   1  *
 * *        ~ (6) (7)  1   4   5  *
 * *        ~ (5)  1   1   2   4  *
 * *           *   *   *   *   * Atlantic
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * <p>
 * 示例 2：
 * <p>
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, i, 0);
            dfs(heights, atlantic, Integer.MIN_VALUE, i, n - 1);
        }
        for (int i = 0; i < n; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, 0, i);
            dfs(heights, atlantic, Integer.MIN_VALUE, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] & atlantic[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    public void dfs(int[][] h, boolean[][] curr, int pre, int x, int y) {
        int m = h.length, n = h[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || curr[x][y] || h[x][y] < pre) {
            return;
        }
        curr[x][y] = true;
        dfs(h, curr, h[x][y], x - 1, y);
        dfs(h, curr, h[x][y], x + 1, y);
        dfs(h, curr, h[x][y], x, y - 1);
        dfs(h, curr, h[x][y], x, y + 1);
    }

}
