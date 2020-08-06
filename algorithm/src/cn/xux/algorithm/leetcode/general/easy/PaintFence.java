package cn.xux.algorithm.leetcode.general.easy;

/**
 * 粉刷篱笆
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 * Return the total number of ways you can paint the fence.
 * Note:
 * n and k are non-negative integers.
 *
 * 分析，f(n) = (k-1)(f(n-1)+f(n-2))
 */
public class PaintFence {

    public int solution1(int n, int k) {
        if(n<=1||k<1) {
            return n*k;
        }
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k*k;
        for(int i=2;i<n;i++) {
            dp[n] = (k-1)*(dp[n-1]+dp[n-2]);
        }
        return dp[n-1];
    }
    /**这样更节省空间*/
    public int solution2(int n, int k) {
        if(n<=1||k<1) {
            return n*k;
        }

        int dp[] = {0, k , k*k, 0};

        if(n <= 2)
            return dp[n];

        for(int i = 2; i < n; i++){
            dp[3] = (k - 1) * (dp[1] + dp[2]);
            dp[1] = dp[2];
            dp[2] = dp[3];
        }

        return dp[3];
    }

    public int solution3(int n, int k) {
        if(n<=1||k<1) {
            return n*k;
        }
        int diff = k * ( k - 1 );
        int same = k;
        int temp;
        for ( int i = 2; i < n; i ++ ){
            temp = diff;
            diff = ( diff + same ) * ( k - 1 );
            same = temp;
        }
        return diff + same;
    }

}
