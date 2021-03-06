package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Arrays;

/**
 *  1198. 找出所有行中最小公共元素
 * 给你一个矩阵 mat，其中每一行的元素都已经按 递增 顺序排好了。
 * 请你帮忙找出在所有这些行中 最小的公共元素。
 * 如果矩阵中没有这样的公共元素，就请返回 -1。
 *
 * 示例：
 * 输入：mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * 输出：5
 *
 * 提示：
 * 1 <= mat.length, mat[i].length <= 500
 * 1 <= mat[i][j] <= 10^4
 * mat[i] 已按递增顺序排列。
 */
public class FindSmallestCommonElementInAllRows {

    public int smallestCommonElement(int[][] mat) {
        for(int k=0;k<mat[0].length;k++) {
            int target = mat[0][k];
            boolean flag = true;
            for (int[] arr : mat) {
                int idx = Arrays.binarySearch(arr, target);
                if (idx < 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                return target;
            }
        }
        return -1;
    }

}
