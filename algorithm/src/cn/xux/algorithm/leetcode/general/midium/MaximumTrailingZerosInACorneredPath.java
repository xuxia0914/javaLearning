package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2245. 转角路径的乘积中最多能有几个尾随零
 * 给你一个二维整数数组 grid ，
 * 大小为 m x n，其中每个单元格都含一个正整数。
 * <p>
 * 转角路径 定义为：包含至多一个弯的一组相邻单元。
 * 具体而言，路径应该完全 向水平方向 或者 向竖直方向 移动过弯（如果存在弯），
 * 而不能访问之前访问过的单元格。在过弯之后，
 * 路径应当完全朝 另一个 方向行进：如果之前是向水平方向，
 * 那么就应该变为向竖直方向；反之亦然。当然，同样不能访问之前已经访问过的单元格。
 * <p>
 * 一条路径的 乘积 定义为：路径上所有值的乘积。
 * <p>
 * 请你从 grid 中找出一条乘积中尾随零数目最多的转角路径，
 * 并返回该路径中尾随零的数目。
 * <p>
 * 注意：
 * <p>
 * 水平 移动是指向左或右移动。
 * 竖直 移动是指向上或下移动。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
 * 输出：3
 * 解释：左侧的图展示了一条有效的转角路径。
 * 其乘积为 15 * 20 * 6 * 1 * 10 = 18000 ，共计 3 个尾随零。
 * 可以证明在这条转角路径的乘积中尾随零数目最多。
 * <p>
 * 中间的图不是一条有效的转角路径，因为它有不止一个弯。
 * 右侧的图也不是一条有效的转角路径，因为它需要重复访问已经访问过的单元格。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：grid = [[4,3,2],[7,6,1],[8,8,8]]
 * 输出：0
 * 解释：网格如上图所示。
 * 不存在乘积含尾随零的转角路径。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 1 <= grid[i][j] <= 1000
 */
public class MaximumTrailingZerosInACorneredPath {

    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // cnt[i][j][0]: grid[i][j]的约数2的个数
        // cnt[i][j][1]: grid[i][j]的约数5的个数
        int[][][] cnt = new int[m][n][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int c2 = 0;
                while (grid[i][j] % 2 == 0) {
                    c2++;
                    grid[i][j] >>= 1;
                }
                cnt[i][j][0] = c2;
                int c5 = 0;
                while (grid[i][j] % 5 == 0) {
                    c5++;
                    grid[i][j] /= 5;
                }
                cnt[i][j][1] = c5;
            }
        }
        // preSum[i][j] 表示grid[i][j]往四个方向的约数2、5的个数的前缀和
        int[][][][] preSum = new int[m][n][4][2];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            preSum[i][0][0][0] = cnt[i][0][0];
            preSum[i][0][0][1] = cnt[i][0][1];
            for (int j = 1; j < n; j++) {
                preSum[i][j][0][0] = preSum[i][j - 1][0][0] + cnt[i][j][0];
                preSum[i][j][0][1] = preSum[i][j - 1][0][1] + cnt[i][j][1];
            }
            preSum[i][n - 1][1][0] = cnt[i][n - 1][0];
            preSum[i][n - 1][1][1] = cnt[i][n - 1][1];
            for (int j = n - 2; j >= 0; j--) {
                preSum[i][j][1][0] = preSum[i][j + 1][1][0] + cnt[i][j][0];
                preSum[i][j][1][1] = preSum[i][j + 1][1][1] + cnt[i][j][1];
            }
        }
        for (int j = 0; j < n; j++) {
            preSum[0][j][2][0] = cnt[0][j][0];
            preSum[0][j][2][1] = cnt[0][j][1];
            for (int i = 1; i < m; i++) {
                preSum[i][j][2][0] = preSum[i - 1][j][2][0] + cnt[i][j][0];
                preSum[i][j][2][1] = preSum[i - 1][j][2][1] + cnt[i][j][1];
            }
            preSum[m - 1][j][3][0] = cnt[m - 1][j][0];
            preSum[m - 1][j][3][1] = cnt[m - 1][j][1];
            for (int i = m - 2; i >= 0; i--) {
                preSum[i][j][3][0] = preSum[i + 1][j][3][0] + cnt[i][j][0];
                preSum[i][j][3][1] = preSum[i + 1][j][3][1] + cnt[i][j][1];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = k + 1; l < 4; l++) {
                        ans = Math.max(ans, Math.min(
                                preSum[i][j][k][0] + preSum[i][j][l][0] - cnt[i][j][0],
                                preSum[i][j][k][1] + preSum[i][j][l][1] - cnt[i][j][1]));
                    }
                }
            }
        }
        return ans;
    }

}
