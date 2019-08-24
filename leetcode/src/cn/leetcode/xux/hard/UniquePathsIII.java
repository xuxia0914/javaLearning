package cn.leetcode.xux.hard;

/**
 * On a 2-dimensional grid, there are 4 types of squares:
 *
 * 1 represents the starting square.  There is exactly one starting square.
 * 2 represents the ending square.  There is exactly one ending square.
 * 0 represents empty squares we can walk over.
 * -1 represents obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 * Example 1:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * Example 2:
 *
 * Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * Example 3:
 *
 * Input: [[0,1],[2,0]]
 * Output: 0
 * Explanation:
 * There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 *
 * Note:
 *
 * 1 <= grid.length * grid[0].length <= 20
 */
public class UniquePathsIII {

    int res = 0;

    public int uniquePathsIII(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] start = new int[2];
        int emptyCnt = 0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    start[0] = i;
                    start[1] = j;
                    emptyCnt++;
                }
                if(grid[i][j]==0){
                    emptyCnt++;
                }
            }
        }
        helper(grid, start[0], start[1], emptyCnt, 0);
        return res;

    }

    public void helper(int[][] grid, int i, int j, int emptyCnt, int currCnt) {
        if(grid[i][j]==2) {
            if(currCnt==emptyCnt) {
                res++;
            }
            return;
        }
        if(grid[i][j]==-1) {
            return;
        }
        if(i>0) {
            grid[i][j] = -1;
            helper(grid, i-1, j, emptyCnt, currCnt+1);
            grid[i][j] = 0;
        }
        if(i<grid.length-1) {
            grid[i][j] = -1;
            helper(grid, i+1, j, emptyCnt, currCnt+1);
            grid[i][j] = 0;
        }
        if(j>0) {
            grid[i][j] = -1;
            helper(grid, i, j-1, emptyCnt, currCnt+1);
            grid[i][j] = 0;
        }
        if(j<grid[0].length-1) {
            grid[i][j] = -1;
            helper(grid, i, j+1, emptyCnt, currCnt+1);
            grid[i][j] = 0;
        }
    }

    public static void main(String[] args) {
        /*System.out.println(new UniquePathsIII().uniquePathsIII(new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2,-1}
        }));*/

        System.out.println(new UniquePathsIII().uniquePathsIII(new int[][]{
                {1,0,0,0},
                {0,0,0,0},
                {0,0,0,2}
        }));
    }

}
