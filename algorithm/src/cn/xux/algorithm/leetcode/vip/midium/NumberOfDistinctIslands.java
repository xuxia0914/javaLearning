package cn.xux.algorithm.leetcode.vip.midium;

import java.util.*;

/**
 * 694. 不同岛屿的数量（BFS/DFS+set）
 * 给定一个非空01二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，
 * 你可以认为网格的四周被海水包围。
 * 请你计算这个网格中共有多少个形状不同的岛屿。
 * 两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
 *
 * 样例 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * 给定上图，返回结果 1。
 *
 * 样例 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * 给定上图，返回结果 3。
 *
 * 注意:
 * 11
 * 1
 * 和
 *  1
 * 11
 * 是不同的岛屿，因为我们不考虑旋转、翻转操作。
 *
 * 注释 :  二维数组每维的大小都不会超过50。
 */
public class NumberOfDistinctIslands {

    public int numberofDistinctIslands(int[][] grid) {
        // write your code here
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j]==1) {
                    List<int[]> curr = new ArrayList<>();
                    dfs(grid, i, j, curr);
                    set.add(islandsToStr(curr));
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, List<int[]> curr) {
        if(i<0||i>=grid.length||j<0||j>=grid[0].length||grid[i][j]==0) {
            return;
        }
        curr.add(new int[]{i, j});
        grid[i][j] = 0;
        dfs(grid, i-1, j, curr);
        dfs(grid, i+1, j, curr);
        dfs(grid, i, j-1, curr);
        dfs(grid, i, j+1, curr);
    }

    private String islandsToStr(List<int[]> points) {
        StringBuilder sb = new StringBuilder();
        int minRow = 50;
        int minCol = 50;
        for(int[] p : points) {
            minRow = Math.min(minRow, p[0]);
            minCol = Math.min(minCol, p[1]);
        }
        Collections.sort(points, (o1,o2)->o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0]);
        for(int[] p : points) {
            sb.append((p[0]-minRow)*50+p[1]-minCol);
            sb.append('#');
        }
        return sb.toString();
    }

}
