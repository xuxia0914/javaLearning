package cn.xux.algorithm.leetcode.general.midium;

/**
 * 73. 矩阵置零
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * 示例 2:
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
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
