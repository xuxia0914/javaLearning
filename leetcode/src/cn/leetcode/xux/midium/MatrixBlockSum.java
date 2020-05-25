package cn.leetcode.xux.midium;

/**
 * 1314. 矩阵区域和
 * 给你一个 m * n 的矩阵 mat 和一个整数 K ，请你返回一个矩阵 answer ，
 * 其中每个 answer[i][j] 是所有满足下述条件的元素 mat[r][c] 的和：
 * i - K <= r <= i + K, j - K <= c <= j + K
 * (r, c) 在矩阵内。
 *
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * 输出：[[12,21,16],[27,45,33],[24,39,28]]
 *
 * 示例 2：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * 输出：[[45,45,45],[45,45,45],[45,45,45]]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 */
public class MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] preSum = new int[m+1][n+1];
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                preSum[i][j] = mat[i-1][j-1]+preSum[i-1][j]+preSum[i][j-1]-preSum[i-1][j-1];
            }
        }
        int[][] ans = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                int up = Math.max(0, i-K);
                int down = Math.min(m-1, i+K);
                int left = Math.max(0, j-K);
                int right = Math.min(n-1, j+K);
                ans[i][j] = preSum[down+1][right+1]-preSum[down+1][left]
                        -preSum[up][right+1]+preSum[up][left];
            }
        }
        return ans;
    }

}
