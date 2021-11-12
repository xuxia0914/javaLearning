package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1895. 最大的幻方
 * 一个 k x k 的 幻方 指的是一个 k x k 填满整数的方格阵，
 * 且每一行、每一列以及两条对角线的和 全部相等 。
 * 幻方中的整数 不需要互不相同 。显然，每个 1 x 1 的方格都是一个幻方。
 * <p>
 * 给你一个 m x n 的整数矩阵 grid ，请你返回矩阵中 最大幻方 的 尺寸 （即边长 k）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * 输出：3
 * 解释：最大幻方尺寸为 3 。
 * 每一行，每一列以及两条对角线的和都等于 12 。
 * - 每一行的和：5+1+6 = 5+4+3 = 2+7+3 = 12
 * - 每一列的和：5+5+2 = 1+4+7 = 6+3+3 = 12
 * - 对角线的和：5+4+3 = 6+4+2 = 12
 * 示例 2：
 * <p>
 * <p>
 * 输入：grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 106
 */
public class LargestMagicSquare {

    public static void main(String[] args) {
        System.out.println(new LargestMagicSquare().largestMagicSquare(
                new int[][]{{7,1,4,5,6},{2,5,1,6,4},{1,5,4,3,2},{1,2,7,3,4}}
        ));
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] preSum1 = new int[m][n + 1];
        int[][] preSum2 = new int[m + 1][n];
        int[][] preSum3 = new int[m + 1][n + 1];
        int[][] preSum4 = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                preSum1[i][j + 1] = preSum1[i][j] + grid[i][j];
                preSum2[i + 1][j] = preSum2[i][j] + grid[i][j];
                preSum3[i + 1][j + 1] = preSum3[i][j] + grid[i][j];
                preSum4[i + 1][n - j - 1] = preSum4[i][n - j] + grid[i][n - j - 1];
            }
        }
        for (int len = Math.min(m, n); len > 1; len--) {
            for (int pos = 0; pos < (m - len + 1) * (n - len + 1); pos++) {
                int i = pos / (n - len + 1);
                int j = pos % (n - len + 1);
                int tar = preSum3[i + len][j + len] - preSum3[i][j];
                if (preSum4[i + len][j] - preSum4[i][j + len] != tar) {
                    continue;
                }
                boolean flag = true;
                for (int k = 0; k < len; k++) {
                    if (preSum1[i + k][j + len] - preSum1[i + k][j] != tar
                            || preSum2[i + len][j + k] - preSum2[i][j + k] != tar) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return len;
                }
            }
        }
        return 1;
    }

}
