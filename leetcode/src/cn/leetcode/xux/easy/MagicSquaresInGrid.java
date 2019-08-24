package cn.leetcode.xux.easy;

/**
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
 * Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
 * Example 1:
 * Input: [[4,3,8,4],
 *         [9,5,1,9],
 *         [2,7,6,2]]
 * Output: 1
 * Explanation:
 * The following subgrid is a 3 x 3 magic square:
 * 438
 * 951
 * 276
 * while this one is not:
 * 384
 * 519
 * 762
 * In total, there is only one magic square inside the given grid.
 * Note:
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * 0 <= grid[i][j] <= 15
 */
public class MagicSquaresInGrid {

    public int numMagicSquaresInside(int[][] grid) {
        if(grid==null||grid.length<3||grid[0].length<3) {
            return 0;
        }
        int res = 0;
        for(int i=1;i<grid.length-1;i++) {
            for(int j=1;j<grid[0].length-1;j++) {
                if(grid[i][j]==5) {
                    if(helper(grid, i, j)) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    public boolean helper(int[][] grid, int i, int j) {
        boolean[] flags = new boolean[9];
        for(int k=i-1;k<=i+1;k++) {
            int sum = 0;
            for(int l=j-1;l<=j+1;l++) {
                if(grid[k][l]<1||grid[k][l]>9||flags[grid[k][l]-1]) {
                    return false;
                }
                flags[grid[k][l]-1] = true;
                sum += grid[k][l];
            }
            if(sum!=15) {
                return false;
            }
        }

        for(int k=j-1;k<=j+1;k++) {
            int sum = 0;
            for(int l=i-1;l<=i+1;l++) {
                sum += grid[l][k];
            }
            if(sum!=15) {
                return false;
            }
        }

        if(grid[i-1][j-1]+grid[i+1][j+1]!=10
                ||grid[i-1][j+1]+grid[i+1][j-1]!=10) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new MagicSquaresInGrid().numMagicSquaresInside(new int[][]{
                {4,3,8,4},
                {9,5,1,9},
                {2,7,6,2}
        }));
    }

}
