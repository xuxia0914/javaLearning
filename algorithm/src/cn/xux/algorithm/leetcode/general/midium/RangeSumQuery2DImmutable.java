package cn.xux.algorithm.leetcode.general.midium;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，
 * 该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * Range Sum Query 2D
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，
 * 右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 *
 * 示例:
 * 给定 matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 *
 * 说明:
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 */
public class RangeSumQuery2DImmutable {

    public int[][] dp;

    public RangeSumQuery2DImmutable(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return;
        }
        dp = new int[matrix.length+1][matrix[0].length+1];
        for(int i=1;i<dp.length;i++) {
            for(int j=1; j<dp[0].length;j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2+1][col2+1] - dp[row1][col2+1] - dp[row2+1][col1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        RangeSumQuery2DImmutable r = new RangeSumQuery2DImmutable(new int[0][0]);
        /*RangeSumQuery2DImmutable r = new RangeSumQuery2DImmutable(new int[][]{
            {3, 0, 1, 4, 2},
            {5, 6, 3, 2, 1},
            {1, 2, 0, 1, 5},
            {4, 1, 0, 1, 7},
            {1, 0, 3, 0, 5}
        });
        System.out.println(r.sumRegion(2, 1, 4, 3));
        System.out.println(r.sumRegion(1, 1, 2, 2));
        System.out.println(r.sumRegion(1, 2, 2, 4));*/
    }

}
