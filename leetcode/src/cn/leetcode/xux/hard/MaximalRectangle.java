package cn.leetcode.xux.hard;

import java.util.Arrays;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例:
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0||matrix[0].length==0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];
        Arrays.fill(left, 0);
        Arrays.fill(right, n);
        Arrays.fill(height, 0);
        int maxA = 0;
        for(int i=0;i<m;i++) {
            int currLeft = 0;
            int currRight = n;

            for(int j=0;j<n;j++) {
                if(matrix[i][j]=='1') {
                    height[j]++;
                }else {
                    height[j] = 0;
                }
            }

            for(int j=0;j<n;j++) {
                if(matrix[i][j]=='1') {
                    left[j] = Math.max(currLeft, left[j]);
                }else {
                    left[j] = 0;
                    currLeft = j+1;
                }
            }

            for(int j=n-1;j>=0;j--) {
                if(matrix[i][j]=='1') {
                    right[j] = Math.min(currRight, right[j]);
                }else {
                    right[j] = n;
                    currRight = j;
                }
            }

            for(int j=0;j<n;j++) {
                maxA = Math.max(maxA, (right[j]-left[j])*height[j]);
            }
        }
        return maxA;
    }

}
