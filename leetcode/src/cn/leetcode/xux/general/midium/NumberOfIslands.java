package cn.leetcode.xux.general.midium;

/**
 * 200. 岛屿数量
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int res = 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]=='1') {
                    res++;
                    helper(grid, i, j);
                }
            }
        }
        return res;
    }

    public void helper(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        if(i>0&&grid[i-1][j]=='1') {
            helper(grid, i-1, j);
        }
        if(i<grid.length-1&&grid[i+1][j]=='1') {
            helper(grid, i+1, j);
        }
        if(j>0&&grid[i][j-1]=='1') {
            helper(grid, i, j-1);
        }
        if(j<grid[0].length-1&&grid[i][j+1]=='1') {
            helper(grid, i, j+1);
        }
    }

}