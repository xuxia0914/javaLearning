package cn.xux.algorithm.leetcode.general.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. 二进制矩阵中的最短路径
 * 在一个 N × N 的方形网格中，每个单元格有两种状态：空（0）或者阻塞（1）。
 * 一条从左上角到右下角、长度为 k 的畅通路径，由满足下述条件的单元格 C_1, C_2, ..., C_k 组成：
 * 相邻单元格 C_i 和 C_{i+1} 在八个方向之一上连通（此时，C_i 和 C_{i+1} 不同且共享边或角）
 * C_1 位于 (0, 0)（即，值为 grid[0][0]）
 * C_k 位于 (N-1, N-1)（即，值为 grid[N-1][N-1]）
 * 如果 C_i 位于 (r, c)，则 grid[r][c] 为空（即，grid[r][c] == 0）
 * 返回这条从左上角到右下角的最短畅通路径的长度。如果不存在这样的路径，返回 -1 。
 *
 * 示例 1：
 * 输入：[[0,1],[1,0]]
 * 输出：2
 *
 * 示例 2：
 * 输入：[[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 *
 * 提示：
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 */
public class ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0]==1||grid[n-1][n-1]==1) {
            return -1;
        }
        if(n==1) return 1;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        visited[0][0] = true;
        int level = 0;
        int[][] neis = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        while(!queue.isEmpty()) {
            level++;
            int size = queue.size();
            while(size-->0) {
                int[] curr = queue.poll();
                for(int[] nei : neis) {
                    int i = curr[0]+nei[0];
                    int j = curr[1]+nei[1];
                    if(i>=0&&i<n&&j>=0&&j<n&&!visited[i][j]&&grid[i][j]==0) {
                        if(i==n-1&&j==n-1) {
                            return level+1;
                        }
                        queue.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
        }
        return -1;
    }

}
