package cn.leetcode.xux.general.midium;

/**
 * 498. 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 * 示例:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * 解释:
 *
 * 说明:
 * 给定矩阵中的元素总数不会超过 100000 。
 */
public class DiagonalTraverse {

    public static void main(String[] args) {
        new DiagonalTraverse().findDiagonalOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0;
        int j = 0;
        boolean upRight = true;
        int[] result = new int[m*n];
        int idx = 0;
        while(i<m&&j<n) {
            result[idx++] = matrix[i][j];
            if(upRight) {
                if(i>0&&j<n-1) {
                    i--;
                    j++;
                }else if(j<n-1) {
                    j++;
                    upRight = false;
                }else {
                    i++;
                    upRight = false;
                }
            }else {
                if(i<m-1&&j>0) {
                    i++;
                    j--;
                }else if(i<m-1) {
                    i++;
                    upRight = true;
                }else {
                    j++;
                    upRight = true;
                }
            }
        }
        return result;
    }

}
