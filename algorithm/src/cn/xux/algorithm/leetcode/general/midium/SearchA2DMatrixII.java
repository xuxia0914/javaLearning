package cn.xux.algorithm.leetcode.general.midium;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。
 *
 * 该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 */
public class SearchA2DMatrixII {

    public static boolean solution(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return false;
        }
        int m=matrix.length, n=matrix[0].length, x=0, y=n-1;
        while(x<m&&y>=0) {
            if(matrix[x][y]==target) {
                return true;
            }else if(matrix[x][y]>target) {
                y--;
            }else {
                x++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 2));
    }

}
