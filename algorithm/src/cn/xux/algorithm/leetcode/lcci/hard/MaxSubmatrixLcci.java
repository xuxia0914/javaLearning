package cn.xux.algorithm.leetcode.lcci.hard;

/**
 * 面试题 17.24. 最大子矩阵
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，
 * 编写代码找出元素总和最大的子矩阵。
 * <p>
 * 返回一个数组 [r1, c1, r2, c2]，
 * 其中 r1, c1 分别代表子矩阵左上角的行号和列号，
 * r2, c2 分别代表右下角的行号和列号。
 * 若有多个满足条件的子矩阵，返回任意一个均可。
 * <p>
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例：
 * <p>
 * 输入：
 * [
 * [-1,0],
 * [0,-1]
 * ]
 * 输出：[0,1,0,1]
 * 解释：输入中标粗的元素即为输出所表示的矩阵
 * <p>
 * <p>
 * 说明：
 * <p>
 * 1 <= matrix.length, matrix[0].length <= 200
 */
public class MaxSubmatrixLcci {

    public int[] getMaxMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        //二维前缀和
        int[][] preSum = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                preSum[i][j] = matrix[i - 1][j - 1] + preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1];
            }
        }
        //开始最大子序和
        int gobalMax = Integer.MIN_VALUE;
        int[] ret = new int[4];
        //先固定上下两条边
        for (int top = 0; top < n; top++) {
            for (int bottom = top; bottom < n; bottom++) {
                int localMax = 0, left = 0;
                //然后从左往右一遍扫描找最大子序和
                for (int right = 0; right < m; right++) {
                    //利用presum快速求出localMax
                    localMax = preSum[bottom + 1][right + 1] + preSum[top][left] - preSum[bottom + 1][left] - preSum[top][right + 1];
                    //如果比gobal大，更新
                    if (gobalMax < localMax) {
                        gobalMax = localMax;
                        ret[0] = top;
                        ret[1] = left;
                        ret[2] = bottom;
                        ret[3] = right;
                    }
                    //如果不满0，前面都舍弃，从新开始计算，left更新到right+1，right下一轮递增之后left==right
                    if (localMax < 0) {
                        localMax = 0;
                        left = right + 1;
                    }
                }
            }
        }
        return ret;
    }

}
