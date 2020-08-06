package cn.xux.algorithm.leetcode.general.hard;

/**
 * 329. 矩阵中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 *
 * 示例 2:
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 */
public class LongestIncreasingPathInAMatrix {

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix lip = new LongestIncreasingPathInAMatrix();
        System.out.println(lip.longestIncreasingPath(new int[][]{
                {3,4,5},
                {3,2,6},
                {2,2,1}
        }));
    }

    //记忆化的递归 dfs
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        mem = new int[m][n];
        int ans = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                ans = Math.max(ans, dfs(matrix, i, j));
            }
        }
        return ans;
    }

    int[][] mem;

    private int dfs(int[][] matrix, int i, int j) {
        int m = matrix.length;
        int n = matrix[0].length;
        if(i<0||i>=m||j<0||j>=n) {
            return 0;
        }
        if(mem[i][j]!=0) {
            return mem[i][j];
        }
        int ans = 1;
        if(i>0&&matrix[i][j]>matrix[i-1][j]) {
            ans = Math.max(ans, 1+dfs(matrix, i-1, j));
        }
        if(i<m-1&&matrix[i][j]>matrix[i+1][j]) {
            ans = Math.max(ans, 1+dfs(matrix, i+1, j));
        }
        if(j>0&&matrix[i][j]>matrix[i][j-1]) {
            ans = Math.max(ans, 1+dfs(matrix, i, j-1));
        }
        if(j<n-1&&matrix[i][j]>matrix[i][j+1]) {
            ans = Math.max(ans, 1+dfs(matrix, i, j+1));
        }
        mem[i][j] = ans;
        return ans;
    }

}
