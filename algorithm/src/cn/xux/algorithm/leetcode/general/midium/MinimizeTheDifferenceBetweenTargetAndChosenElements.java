package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 1981. 最小化目标值与所选元素的差
 * 给你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。
 * 从矩阵的 每一行 中选择一个整数，
 * 你的目标是 最小化 所有选中元素之 和 与目标值 target 的 绝对差 。
 * 返回 最小的绝对差 。
 * a 和 b 两数字的 绝对差 是 a - b 的绝对值。
 *
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]], target = 13
 * 输出：0
 * 解释：一种可能的最优选择方案是：
 * - 第一行选出 1
 * - 第二行选出 5
 * - 第三行选出 7
 * 所选元素的和是 13 ，等于目标值，所以绝对差是 0 。
 *
 * 示例 2：
 * 输入：mat = [[1],[2],[3]], target = 100
 * 输出：94
 * 解释：唯一一种选择方案是：
 * - 第一行选出 1
 * - 第二行选出 2
 * - 第三行选出 3
 * 所选元素的和是 6 ，绝对差是 94 。
 *
 * 示例 3：
 * 输入：mat = [[1,2,9,8,7]], target = 6
 * 输出：1
 * 解释：最优的选择方案是选出第一行的 7 。
 * 绝对差是 1 。
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 70
 * 1 <= mat[i][j] <= 70
 * 1 <= target <= 800
 */
public class MinimizeTheDifferenceBetweenTargetAndChosenElements {

    public static void main(String[] args) {
        System.out.println(new MinimizeTheDifferenceBetweenTargetAndChosenElements()
                .minimizeTheDifference(new int[][]{
                        {10,3,7,7,9,6,9,8,9,5},
                        {1,1,6,8,6,7,7,9,3,9},
                        {3,4,4,1,3,6,3,3,9,9},
                        {6,9,9,3,8,7,9,6,10,6}},
                5));
    }

    public int minimizeTheDifference(int[][] mat, int target) {
        boolean[][] dp=new boolean[mat.length+1][4900];
        dp[0][0]=true;

        for(int i=1;i<=mat.length;i++){
            for(int j=0;j<4900;j++){
                for(int k=0;k<mat[0].length;k++){
                    if(j-mat[i-1][k]>=0 && dp[i-1][j-mat[i-1][k]]==true){
                        dp[i][j]=true;
                        break;
                    }
                    dp[i][j]=false;
                }
            }
        }
        int ret=Integer.MAX_VALUE;
        for(int j=0;j<4900;j++){
            if(dp[mat.length][j]==true){
                ret=Math.min(Math.abs(target-j),ret);
            }
        }
        return ret;
    }

}
