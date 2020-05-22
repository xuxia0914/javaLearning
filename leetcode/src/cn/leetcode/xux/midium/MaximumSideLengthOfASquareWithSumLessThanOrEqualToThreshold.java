package cn.leetcode.xux.midium;

/**
 * 1292. 元素和小于等于阈值的正方形的最大边长
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长；如果没有这样的正方形区域，则返回 0 。
 *
 * 示例 1：
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 * 解释：总和小于 4 的正方形的最大边长为 2，如图所示。
 *
 * 示例 2：
 * 输入：mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
 * 输出：0
 *
 * 示例 3：
 * 输入：mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
 * 输出：3
 *
 * 示例 4：
 * 输入：mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
 * 输出：2
 *
 * 提示：
 * 1 <= m, n <= 300
 * m == mat.length
 * n == mat[i].length
 * 0 <= mat[i][j] <= 10000
 * 0 <= threshold <= 10^5
 */
public class MaximumSideLengthOfASquareWithSumLessThanOrEqualToThreshold {

    public int maxSideLength(int[][] mat, int threshold) {
        int result = 0;
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=m;i++) {
            for(int j=1;j<=n;j++) {
                dp[i][j] = mat[i-1][j-1]+dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1];
                int left = result+1;
                int right = Math.min(i, j);
                while(left<right) {
                    int mid = (left+right+1)/2;
                    int sum = dp[i][j]-dp[i-mid][j]-dp[i][j-mid]+dp[i-mid][j-mid];
                    if(sum<=threshold) {
                        left = mid;
                    }else {
                        right = mid-1;
                    }
                }
                if(left==right&&dp[i][j]-dp[i-left][j]-dp[i][j-left]+dp[i-left][j-left]<=threshold) {
                    result = left;
                }
            }
        }
        return result;
    }

}
