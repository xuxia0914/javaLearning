package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 太平洋大西洋水流
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * Note:
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * Example:
 * Given the following 5x5 matrix:
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 * Return:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {

    public static List<int[]> solution(int[][] matrix) {
        List<int[]> result = new ArrayList<int[]>();
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return result;
        }
        int m=matrix.length, n=matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for(int i=0;i<m;i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, n-1);
        }
        for(int i=0;i<n;i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, m-1, i);
        }
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(pacific[i][j]&atlantic[i][j]) {
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }

    public static void dfs(int[][] matrix, boolean[][] curr, int pre, int x, int y) {
        int m=matrix.length, n=matrix[0].length;
        if(x<0||x>=m||y<0||y>=n||curr[x][y]||matrix[x][y]<pre) {
            return;
        }
        curr[x][y] = true;
        dfs(matrix, curr, matrix[x][y], x-1, y);
        dfs(matrix, curr, matrix[x][y], x+1, y);
        dfs(matrix, curr, matrix[x][y], x, y-1);
        dfs(matrix, curr, matrix[x][y], x, y+1);
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        List<int[]> result = solution(matrix);
        for(int[] point : result) {
            System.out.println(point[0]+", "+point[1]);
        }
    }

}
