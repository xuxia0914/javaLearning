package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 311. 稀疏矩阵的乘法
 * 给你两个 稀疏矩阵 A 和 B，请你返回 AB 的结果。
 * 你可以默认 A 的列数等于 B 的行数。
 * 请仔细阅读下面的示例。
 * 示例：
 * 输入：
 * A = [
 *   [ 1, 0, 0],
 *   [-1, 0, 3]
 * ]
 * B = [
 *   [ 7, 0, 0 ],
 *   [ 0, 0, 0 ],
 *   [ 0, 0, 1 ]
 * ]
 * 输出：
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 */
public class SparseMatrixMultiplication {

    public int[][] Multiply(int[][] A, int[][] B) {
        int m1 = A.length;
        int n1 = A[0].length;
        int n2 = B[0].length;
        int[][] ans = new int[m1][n2];
        for(int i=0;i<m1;i++) {
            for(int j=0;j<n1;j++) {
                if(A[i][j]!=0) {
                    for(int k=0;k<n2;k++) {
                        ans[i][k] += A[i][j]*B[j][k];
                    }
                }
            }
        }
        return ans;
    }

}
