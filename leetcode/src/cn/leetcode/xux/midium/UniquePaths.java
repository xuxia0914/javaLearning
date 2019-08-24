package cn.leetcode.xux.midium;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 *
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 *
 * Input: m = 7, n = 3
 * Output: 28
 */
public class UniquePaths {

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
     * Memory Usage: 32.9 MB, less than 5.10% of Java online submissions for Unique Paths.
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if(m<1||n<1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for(int i=0;i<m;i++) {
            dp[i][0] = 1;
        }
        for(int i=0;i<n;i++) {
            dp[0][i] = 1;
        }
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
     * Memory Usage: 33 MB, less than 5.10% of Java online submissions for Unique Paths.
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths1(int m, int n) {
        if(m<1||n<1) {
            return 0;
        }
        if(m==1||n==1) {
            return 1;
        }
        if(n>m) {
            int tmp = m;
            m = n;
            n = tmp;
        }
        return (int)(helper(m+n-2, m)/helper(n-1, 2));
    }

    public long helper(int m, int n) {
        long res = 1;
        while(m>=n) {
            res *=m;
            m--;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths1(10, 10));
    }

}
