package cn.leetcode.xux.midium;

/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * Follow up:
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 *
 * 给定一个矩阵，把零值所在的行和列都置为零。例如：
 * 1 2 3
 * 1 0 3
 * 1 1 1
 * 操作之后变为
 * 1 0 3
 * 0 0 0
 * 1 0 1
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRow = false;
        boolean firstColumn = false;
        for(int i=0;i<m;i++) {
            if(matrix[i][0]==0) {
                firstColumn = true;
                break;
            }
        }
        for(int j=0;j<n;j++) {
            if(matrix[0][j]==0) {
                firstRow = true;
                break;
            }
        }
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                if(matrix[i][j]==0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i=1;i<m;i++) {
            if(matrix[i][0]==0) {
                for(int j=1;j<n;j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for(int j=1;j<n;j++) {
            if(matrix[0][j]==0) {
                for(int i=1;i<m;i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(firstColumn) {
            for(int i=0;i<m;i++) {
                matrix[i][0] = 0;
            }
        }
        if(firstRow) {
            for(int j=0;j<n;j++) {
                matrix[0][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3,0},
                {1,0,3,2},
                {0,1,1,5}
        };
        new SetMatrixZeroes().setZeroes(matrix);
        for(int[] ia : matrix) {
            for(int i : ia) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

}
