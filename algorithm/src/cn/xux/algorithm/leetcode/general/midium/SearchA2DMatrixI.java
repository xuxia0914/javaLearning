package cn.xux.algorithm.leetcode.general.midium;

/**
 * 搜索一个二维矩阵之一
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 * Consider the following matrix:
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */
public class SearchA2DMatrixI {

    public static boolean solution(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return false;
        }
        int m=matrix.length, n=matrix[0].length, left=0, right=m*n-1;
        while(left<=right) {
            int mid = (left+right)/2;
            if(matrix[mid/n][mid%n]>target) {
                right = mid-1;
            }else if(matrix[mid/n][mid%n]<target) {
                left = mid+1;
            }else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        }, 3));
    }

}
