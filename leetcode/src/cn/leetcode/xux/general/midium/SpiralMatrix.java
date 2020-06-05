package cn.leetcode.xux.general.midium;

import java.util.LinkedList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 *
 * 示例 2:
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        if(matrix==null||matrix.length==0||matrix[0].length==0) {
            return result;
        }
        int up = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(up<=bottom&&left<=right) {
            for(int i=left;i<=right;i++) {
                result.add(matrix[up][i]);
            }
            for(int i=up+1;i<=bottom;i++) {
                result.add(matrix[i][right]);
            }
            if(up<bottom&&left<right) {
                for(int i=right-1;i>=left;i--) {
                    result.add(matrix[bottom][i]);
                }
                for(int i=bottom-1;i>=up+1;i--) {
                    result.add(matrix[i][left]);
                }
            }
            up++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }

}
