package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 361. 轰炸敌人（前缀和DP）
 * 想象一下炸弹人游戏，在你面前有一个二维的网格来表示地图，网格中的格子分别被以下三种符号占据：
 * 'W' 表示一堵墙
 * 'E' 表示一个敌人
 * '0'（数字 0）表示一个空位
 * 请你计算一个炸弹最多能炸多少敌人。
 * 由于炸弹的威力不足以穿透墙体，炸弹只能炸到同一行和同一列没被墙体挡住的敌人。
 * 注意：你只能把炸弹放在一个空的格子里
 *
 * 示例:
 * 输入: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * 输出: 3
 * 解释: 对于如下网格
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 * 假如在位置 (1,1) 放置炸弹的话，可以炸到 3 个敌人
 */
public class BombEnemy {

    public int maxKilledEnemies(char[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m][n][4];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]=='0'&&i>0) {
                    dp[i][j][0] = grid[i-1][j]=='W'?0:(dp[i-1][j][0]+grid[i-1][j]=='E'?1:0);
                }
                if(grid[i][j]=='0'&&j>0) {
                    dp[i][j][1] = grid[i][j-1]=='W'?0:(dp[i][j-1][1]+grid[i][j-1]=='E'?1:0);
                }
            }
        }
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                if(grid[i][j]=='0'&&i<m-1) {
                    dp[i][j][2] = grid[i+1][j]=='W'?0:(dp[i+1][j][2]+grid[i+1][j]=='E'?1:0);
                }
                if(grid[i][j]=='0'&&j<n-1) {
                    dp[i][j][3] = grid[i][j+1]=='W'?0:(dp[i][j+1][3]+grid[i][j+1]=='E'?1:0);
                }
            }
        }
        int ans = 0;
        for(int i=m-1;i>=0;i--) {
            for(int j=n-1;j>=0;j--) {
                if(grid[i][j]==0) {
                    ans = Math.max(ans, dp[i][j][0]+dp[i][j][1]+dp[i][j][2]+dp[i][j][3]);
                }
            }
        }
        return ans;
    }

}
