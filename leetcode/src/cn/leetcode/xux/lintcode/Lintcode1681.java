package cn.leetcode.xux.lintcode;

/**
 * 1681. 切蛋糕
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 一块n*m的矩形蛋糕，有k个草莓，现在要将蛋糕切开使每块蛋糕上都恰有一个草莓（这意味着不能切出不含草莓的蛋糕块），要求只能水平切或竖直切，求最短的刀切长度。
 *
 * 样例
 * 样例 1:
 *
 * 输入: n = 3, m = 4, k = 3, mp = [[1, 2], [2, 3], [3, 2]]
 * 输出: 5
 * 解释:
 * 先在中间竖切一刀，然后在把有2个草莓的一半切一刀。
 * 样例 2:
 *
 * 输入: n = 2, m = 2, k = 2, mp = [[1, 1], [2, 2]]
 * 输出: 2
 * 解释:
 * 在中间竖切一刀
 * 注意事项
 * n, m 不超过20
 * 二维数组 mp 为 k 个草莓所在的坐标
 */
public class Lintcode1681 {

    public static void main(String[] args) {
        Lintcode1681 l = new Lintcode1681();
//        System.out.println(l.getTheShortestCutLength(2,2,2, new int[][]{{1,1}, {2,2}}));
        System.out.println(l.getTheShortestCutLength(3,4,3, new int[][]{{1,2}, {2,3}, {3,2}}));
    }

    public int getTheShortestCutLength(int n, int m, int k, int[][] mp) {
        // Write your code here.
        if(n<1||m<1||k<2) {
            return 0;
        }
        int[][] board = new int[n][m];
        for(int[] p : mp) {
            board[p[0]-1][p[1]-1] = 1;
        }
        int[][][][] dp = new int[n][n][m][m];
        for(int h=1;h<=n;h++) {
            for(int w=1;w<=m;w++) {
                for(int u=0;u+h<=n;u++) {
                    for(int l=0;l+w<=m;l++) {
                        int b = u+h-1;
                        int r = l+w-1;
                        int sum = sum(board, u ,b, l, r);
                        if(sum==0) {
                            dp[u][b][l][r] = -1;
                        }else if(sum>1) {
                            dp[u][b][l][r] = 2*h*w;
                            if(h==1||w==1) {
                                dp[u][b][l][r] = Math.min(dp[u][b][l][r], sum-1);
                            }else {
                                //横切
                                for(int mid=u;mid<b;mid++) {
                                    if(dp[u][mid][l][r]!=-1&&dp[mid+1][b][l][r]!=-1) {
                                        dp[u][b][l][r] = Math.min(dp[u][b][l][r], dp[u][mid][l][r]+dp[mid+1][b][l][r]+w);
                                    }
                                }
                                //竖切
                                for(int mid=l;mid<r;mid++) {
                                    if(dp[u][b][l][mid]!=-1&&dp[u][b][mid+1][r]!=-1) {
                                        dp[u][b][l][r] = Math.min(dp[u][b][l][r], dp[u][b][l][mid]+dp[u][b][mid+1][r]+h);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return dp[0][n-1][0][m-1];
    }

    private int sum(int[][] board, int u, int b, int l, int r) {
        if(b<u||l>r) {
            return 0;
        }
        int ans = 0;
        for(int i=u;i<=b;i++) {
            for(int j=l;j<=r;j++) {
                if(board[i][j]==1) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
