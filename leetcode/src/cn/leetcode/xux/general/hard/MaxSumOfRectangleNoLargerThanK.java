package cn.leetcode.xux.general.hard;

import java.util.TreeSet;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 *
 * 示例:
 * 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出: 2
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 *
 * 说明：
 * 矩阵内的矩形区域面积必须大于 0。
 * 如果行数远大于列数，你将如何解答呢？
 */
public class MaxSumOfRectangleNoLargerThanK {

    public static void main(String[] args) {
        System.out.println(new MaxSumOfRectangleNoLargerThanK()
                .maxSumSubmatrix(
                        new int[][]{
                            {1,0,1},
                            {0,-2,3}
                },
                        2

        ));
    }
    // O(n^2*m*lggm)
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        for(int left=0;left<n;left++) {
            int[] rowSum = new int[m];
            for(int right=left;right<n;right++) {
                for(int i=0;i<m;i++) {
                    rowSum[i] += matrix[i][right];
                }
                int[] preSum = new int[m+1];
                for(int i=1;i<=m;i++) {
                    preSum[i] = preSum[i-1]+rowSum[i-1];
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for(int i=1;i<=m;i++) {
                    Integer tar = set.ceiling(preSum[i]-k);
                    if(tar!=null&&preSum[i]-tar>ans) {
                        ans = preSum[i]-tar;
                        if(ans==k) {
                            return k;
                        }
                    }
                    set.add(preSum[i]);
                }
            }
        }
        return ans;
    }

}
