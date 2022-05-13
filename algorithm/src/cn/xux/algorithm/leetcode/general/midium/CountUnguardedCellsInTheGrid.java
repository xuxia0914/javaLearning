package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2257. 统计网格图中没有被保卫的格子数
 * 给你两个整数 m 和 n 表示一个下标从 0 开始的 m x n 网格图。
 * 同时给你两个二维整数数组 guards 和 walls ，
 * 其中 guards[i] = [rowi, coli] 且 walls[j] = [rowj, colj] ，
 * 分别表示第 i 个警卫和第 j 座墙所在的位置。
 * <p>
 * 一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 所有 格子，
 * 除非他们被一座墙或者另外一个警卫 挡住 了视线。
 * 如果一个格子能被 至少 一个警卫看到，那么我们说这个格子被 保卫 了。
 * <p>
 * 请你返回空格子中，有多少个格子是 没被保卫 的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
 * 输出：7
 * 解释：上图中，被保卫和没有被保卫的格子分别用红色和绿色表示。
 * 总共有 7 个没有被保卫的格子，所以我们返回 7 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
 * 输出：4
 * 解释：上图中，没有被保卫的格子用绿色表示。
 * 总共有 4 个没有被保卫的格子，所以我们返回 4 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= m, n <= 105
 * 2 <= m * n <= 105
 * 1 <= guards.length, walls.length <= 5 * 104
 * 2 <= guards.length + walls.length <= m * n
 * guards[i].length == walls[j].length == 2
 * 0 <= rowi, rowj < m
 * 0 <= coli, colj < n
 * guards 和 walls 中所有位置 互不相同 。
 */
public class CountUnguardedCellsInTheGrid {

    public static void main(String[] args) {
        //4
        //6
        //[[0,0],[1,1],[2,3]]
        //[[0,1],[2,2],[1,4]]
        System.out.println(new CountUnguardedCellsInTheGrid()
                .countUnguarded(4, 6, new int[][]{{0, 0}, {1, 1}, {2, 3}}, new int[][]{{0, 1}, {2, 2}, {1, 4}}));
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] state = new int[m][n];
        for (int[] g : guards) {
            state[g[0]][g[1]] = 1;
        }
        for (int[] w : walls) {
            state[w[0]][w[1]] = 2;
        }
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] g : guards) {
            for (int[] d : dir) {
                int x = g[0] + d[0];
                int y = g[1] + d[1];
                while (x >= 0 && x < m && y >= 0 && y < n && (state[x][y] == 0 || state[x][y] == 3)) {
                    state[x][y] = 3;
                    x += d[0];
                    y += d[1];
                }
            }
        }
        int ans = 0;
        for (int[] sta : state) {
            for (int s : sta) {
                ans += s == 0 ? 1 : 0;
            }
        }
        return ans;
    }

}
