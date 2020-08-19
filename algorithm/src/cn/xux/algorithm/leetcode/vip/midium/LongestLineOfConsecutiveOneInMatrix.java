package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 562. 矩阵中最长的连续1线段
 * 给定一个01矩阵 M，找到矩阵中最长的连续1线段。这条线段可以是水平的、垂直的、对角线的或者反对角线的。
 *
 * 示例:
 * 输入:
 * [[0,1,1,0],
 * [0,1,1,0],
 * [0,0,0,1]]
 * 输出: 3
 *
 * 提示: 给定矩阵中的元素数量不会超过 10,000。
 */
public class LongestLineOfConsecutiveOneInMatrix {

    public int longestLine(int[][] M) {
        if(M==null||M.length==0||M[0].length==0) {
            return 0;
        }
        int m = M.length;
        int n = M[0].length;
        int[][][] dp = new int[m][n][4];
        int ans = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(M[i][j]==1) {
                    dp[i][j][0] = i==0?1:dp[i-1][j][0]+1;
                    dp[i][j][1] = j==0?1:dp[i][j-1][1]+1;
                    dp[i][j][2] = i==0||j==0?1:dp[i-1][j-1][2]+1;
                    ans = Math.max(Math.max(ans, dp[i][j][0]), Math.max(dp[i][j][1], dp[i][j][2]));
                }
                if(M[i][n-1-j]==1) {
                    dp[i][n-1-j][3] = i==0||j==0?1:dp[i-1][n-j][3]+1;
                    ans = Math.max(ans, dp[i][n-1-j][3]);
                }
            }
        }
        return ans;
    }

}
