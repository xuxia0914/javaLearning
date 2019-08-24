package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋遍历矩阵
 *Given a matrix of m x n elements (m rows, ncolumns), return all elements of the matrix in spiral order.
 * Example 1:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static List<Integer> solution(int[][] matrix) {
        int minColumn=0, maxColumn=matrix[0].length-1, minRow=0, maxRow=matrix.length-1;
        boolean ifColumn=true, columnAsc=true, rowAsc=true;
        List<Integer> result = new ArrayList<Integer>();
        while(minRow<=maxRow&&minColumn<=maxColumn) {
            if(ifColumn) {
                if(columnAsc) {
                    for(int i=minColumn;i<=maxColumn;i++) {
                        result.add(matrix[minRow][i]);
                    }
                    minRow++;
                }else {
                    for(int i=maxColumn;i>=minColumn;i--) {
                        result.add(matrix[maxRow][i]);
                    }
                    maxRow--;
                }
                columnAsc = !columnAsc;
            }else {
                if(rowAsc) {
                    for(int i=minRow;i<=maxRow;i++) {
                        result.add(matrix[i][maxColumn]);
                    }
                    maxColumn--;
                }else {
                    for(int i=maxRow;i>=minRow;i--) {
                        result.add(matrix[i][minColumn]);
                    }
                    minColumn++;
                }
                rowAsc = !rowAsc;
            }
            ifColumn = !ifColumn;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(
                new int[][]{{1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9}}
        ));
        System.out.println(solution(
                new int[][]{{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10,11,12}}
        ));
        System.out.println(solution(
                new int[][]{{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10,11,12},
                            {13,14,15,16},
                            {17,18,19,20}}
        ));
    }

}
