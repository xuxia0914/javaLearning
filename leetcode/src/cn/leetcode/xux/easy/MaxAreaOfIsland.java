package cn.leetcode.xux.easy;

/**
 * LeetCode 695. Max Area of Island （岛的最大区域）
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * Example 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class MaxAreaOfIsland {

    public static int solution(int[][] island) {
        if(island.length==0||island[0].length==0) {
            return 0;
        }
        int maxArea = 0;
        for(int i=0;i<island.length;i++) {
            for(int j=0;j<island[0].length;j++) {
                if(island[i][j]==1) {
                    int area =currArea(island, i, j);
                    maxArea = area>maxArea?area:maxArea;
                }
            }
        }
        return maxArea;
    }

    public static int currArea(int[][] island, int i, int j) {
        int area = 0;
        if(island[i][j]==1) {
            area++;
            island[i][j]=0;
        }
        if(i>0&&island[i-1][j]==1) {
            area += currArea(island, i-1, j);
        }
        if(j>0&&island[i][j-1]==1) {
            area += currArea(island, i, j-1);
        }
        if(i<island.length-1&&island[i+1][j]==1) {
            area += currArea(island, i+1, j);
        }
        if(j<island[0].length&&island[i][j+1]==1) {
            area += currArea(island, i, j+1);
        }
        return area;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        }));
    }

}
