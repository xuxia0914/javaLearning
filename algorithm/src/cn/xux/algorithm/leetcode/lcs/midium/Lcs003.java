package cn.xux.algorithm.leetcode.lcs.midium;

/**
 * LCS 03. 主题空间
 * 「以扣会友」线下活动所在场地由若干主题空间与走廊组成，
 * 场地的地图记作由一维字符串型数组 grid，字符串中仅包含 "0"～"5" 这 6 个字符。
 * 地图上每一个字符代表面积为 1 的区域，其中 "0" 表示走廊，其他字符表示主题空间。
 * 相同且连续（连续指上、下、左、右四个方向连接）的字符组成同一个主题空间。
 * 假如整个 grid 区域的外侧均为走廊。
 * 请问，不与走廊直接相邻的主题空间的最大面积是多少？如果不存在这样的空间请返回 0。
 *
 * 示例 1:
 * 输入：grid = ["110","231","221"]
 * 输出：1
 * 解释：4 个主题空间中，只有 1 个不与走廊相邻，面积为 1。
 *
 * 示例 2:
 * 输入：grid = ["11111100000","21243101111","21224101221","11111101111"]
 * 输出：3
 * 解释：8 个主题空间中，有 5 个不与走廊相邻，面积分别为 3、1、1、1、2，最大面积为 3。
 *
 * 提示：
 * 1 <= grid.length <= 500
 * 1 <= grid[i].length <= 500
 * grid[i][j] 仅可能是 "0"～"5"
 */
public class Lcs003 {

    int[][] board;
    int m;
    int n;
    int ans;
    boolean nearCorr;
    int cnt;

    public int largestArea(String[] grid) {
        ans = 0;
        m = grid.length;
        n = grid[0].length();
        board = new int[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                board[i][j] = grid[i].charAt(j)-'0';
            }
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j]>0) {
                    nearCorr = false;
                    cnt = 0;
                    dfs(board, i, j, board[i][j]);
                    if(!nearCorr) {
                        ans = Math.max(ans, cnt);
                    }
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] board, int i, int j, int tar) {
        if(i<0||i>=m||j<0||j>=n||board[i][j]==0) {
            nearCorr = true;
            return;
        }
        if(board[i][j]<0||board[i][j]!=tar) {
            return;
        }
        board[i][j] = -board[i][j];
        cnt++;
        dfs(board, i-1, j, tar);
        dfs(board, i+1, j, tar);
        dfs(board, i, j-1, tar);
        dfs(board, i, j+1, tar);
    }

}
