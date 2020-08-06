package cn.xux.algorithm.leetcode.general.easy;

/**
 * 463. 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * 示例 :
 * 输入:[[0,1,0,0],
 *      [1,1,1,0],
 *      [0,1,0,0],
 *      [1,1,0,0]]
 * 输出: 16

 */
public class IslandPerimeter {

    int res = 0;

    public int islandPerimeter(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean flag = false;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    islandPerimeter(grid, new boolean[m][n], i, j);
                    flag = true;
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        return res;
    }

    public void islandPerimeter(int[][] grid, boolean[][] visited, int i, int j) {
        int curr = 4;
        visited[i][j] = true;
        if(i>0&&grid[i-1][j]==1) {
            curr--;
            if(!visited[i-1][j]) {
                islandPerimeter(grid, visited, i-1, j);
            }
        }
        if(j>0&&grid[i][j-1]==1) {
            curr--;
            if(!visited[i][j-1]) {
                islandPerimeter(grid, visited, i, j-1);
            }
        }
        if(i<grid.length-1&&grid[i+1][j]==1) {
            curr--;
            if(!visited[i+1][j]) {
                islandPerimeter(grid, visited, i+1, j);
            }
        }
        if(j<grid[0].length-1&&grid[i][j+1]==1) {
            curr--;
            if(!visited[i][j+1]) {
                islandPerimeter(grid, visited, i, j+1);
            }
        }
        res += curr;
    }

    public static void main(String[] args) {
        System.out.println(new IslandPerimeter().islandPerimeter(new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        }));
    }

}
