package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        if(board==null||board.length<3||board[0].length<2) {
            return ;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] flags = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<n;i++) {
            if(!flags[0][i]&&board[0][i]=='O') {
                flags[0][i] = true;
                queue.offer(new int[]{0, i});
            }
            if(!flags[m-1][i]&&board[m-1][i]=='O') {
                flags[m-1][i] = true;
                queue.offer(new int[]{m-1, i});
            }
        }
        for(int i=1;i<m-1;i++) {
            if(!flags[i][0]&&board[i][0]=='O') {
                flags[i][0] = true;
                queue.offer(new int[]{i, 0});
            }
            if(!flags[i][n-1]&&board[i][n-1]=='O') {
                flags[i][n-1] = true;
                queue.offer(new int[]{i, n-1});
            }
        }
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            if(curr[0]>0&&!flags[curr[0]-1][curr[1]]&&board[curr[0]-1][curr[1]]=='O') {
                flags[curr[0]-1][curr[1]] = true;
                queue.offer(new int[]{curr[0]-1, curr[1]});
            }
            if(curr[0]<m-1&&!flags[curr[0]+1][curr[1]]&&board[curr[0]+1][curr[1]]=='O') {
                flags[curr[0]+1][curr[1]] = true;
                queue.offer(new int[]{curr[0]+1, curr[1]});
            }
            if(curr[1]>0&&!flags[curr[0]][curr[1]-1]&&board[curr[0]][curr[1]-1]=='O') {
                flags[curr[0]][curr[1]-1] = true;
                queue.offer(new int[]{curr[0], curr[1]-1});
            }
            if(curr[1]<n-1&&!flags[curr[0]][curr[1]+1]&&board[curr[0]][curr[1]+1]=='O') {
                flags[curr[0]][curr[1]+1] = true;
                queue.offer(new int[]{curr[0], curr[1]+1});
            }
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(!flags[i][j]&&board[i][j]=='O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

}
