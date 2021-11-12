package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1905. 统计子岛屿
 * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，
 * 它们只包含 0 （表示水域）和 1 （表示陆地）。
 * 一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。
 * 任何矩阵以外的区域都视为水域。
 * <p>
 * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，
 * 也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，
 * 那么我们称 grid2 中的这个岛屿为 子岛屿 。
 * <p>
 * 请你返回 grid2 中 子岛屿 的 数目 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * 输出：3
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
 * 示例 2：
 * <p>
 * <p>
 * 输入：grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * 输出：2
 * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
 * grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] 和 grid2[i][j] 都要么是 0 要么是 1 。
 */
public class CountSubIslands {

    public static void main(String[] args) {
        System.out.println(new CountSubIslands().countSubIslands(
                new int[][]{{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}},
                new int[][]{{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}}
        ));
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;
        m = this.grid1.length;
        n = this.grid1[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.grid2[i][j] == 1 && dfs(i, j)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    int[][] grid1;
    int[][] grid2;
    int m;
    int n;

    private boolean dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid2[i][j] == 0) {
            return true;
        }
        grid2[i][j] = 0;
        return grid1[i][j] == 1
                & dfs(i - 1, j)
                & dfs(i + 1, j)
                & dfs(i, j - 1)
                & dfs(i, j + 1);
    }

}
