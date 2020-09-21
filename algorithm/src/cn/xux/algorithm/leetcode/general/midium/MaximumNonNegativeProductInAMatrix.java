package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1594. 矩阵的最大非负积
 * 给你一个大小为 rows x cols 的矩阵 grid 。
 * 最初，你位于左上角 (0, 0) ，每一步，你可以在矩阵中 向右 或 向下 移动。
 * 在从左上角 (0, 0) 开始到右下角 (rows - 1, cols - 1) 结束的所有路径中，
 * 找出具有 最大非负积 的路径。路径的积是沿路径访问的单元格中所有整数的乘积。
 * 返回 最大非负积 对 109 + 7 取余 的结果。如果最大积为负数，则返回 -1 。
 * 注意，取余是在得到最大积之后执行的。
 *
 * 示例 1：
 * 输入：grid = [[-1,-2,-3],
 *              [-2,-3,-3],
 *              [-3,-3,-2]]
 * 输出：-1
 * 解释：从 (0, 0) 到 (2, 2) 的路径中无法得到非负积，所以返回 -1
 *
 * 示例 2：
 * 输入：grid = [[1,-2,1],
 *              [1,-2,1],
 *              [3,-4,1]]
 * 输出：8
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * 1 * -2 * -4 * 1 = 8)
 *
 * 示例 3：
 * 输入：grid = [[1, 3],
 *              [0,-4]]
 * 输出：0
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * 0 * -4 = 0)
 *
 * 示例 4：
 * 输入：grid = [[ 1, 4,4,0],
 *              [-2, 0,0,1],
 *              [ 1,-1,1,1]]
 * 输出：2
 * 解释：最大非负积对应的路径已经用粗体标出 (1 * -2 * 1 * -1 * 1 * 1 = 2)
 *
 * 提示：
 * 1 <= rows, cols <= 15
 * -4 <= grid[i][j] <= 4
 */
public class MaximumNonNegativeProductInAMatrix {

    public static void main(String[] args) {
        MaximumNonNegativeProductInAMatrix mn = new MaximumNonNegativeProductInAMatrix();
        System.out.println(mn.maxProductPath(new int[][]{{-1,-2,-3},{-2,-3,-3},{-3,-3,-2}}));
    }

    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long[][] dp = new long[n][2];
        dp[0][0] = grid[0][0];
        dp[0][1] = grid[0][0];
        for(int j=1;j<n;j++) {
            dp[j][0] = dp[j-1][0]*grid[0][j];
            dp[j][1] = dp[j-1][0]*grid[0][j];
        }
        for(int i=1;i<m;i++) {
            long[][] newDp = new long[n][2];
            long a = dp[0][0]*grid[i][0];
            long b = dp[0][1]*grid[i][0];
            newDp[0][0] = Math.max(a, b);
            newDp[0][1] = Math.min(a, b);
            for(int j=1;j<n;j++) {
                a = dp[j][0]*grid[i][j];
                b = dp[j][1]*grid[i][j];
                long c = newDp[j-1][0]*grid[i][j];
                long d = newDp[j-1][1]*grid[i][j];
                newDp[j][0] = Math.max(Math.max(a,b), Math.max(c,d));
                newDp[j][1] = Math.min(Math.min(a,b), Math.min(c,d));
            }
            dp = newDp;
        }
        if(dp[n-1][0]<0) {
            return -1;
        }else {
            return (int)(dp[n-1][0]%1000000007);
        }
    }

}
