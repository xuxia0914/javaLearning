package cn.xux.algorithm.leetcode.vip.easy;

/**
 * 256. 粉刷房子
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，
 * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，
 * 以此类推。请你计算出粉刷完所有房子最少的花费成本。
 *
 * 注意：
 * 所有花费均为正整数。
 */
public class PaintHouse {

    public int minCost(int[][] costs) {
        if(costs==null||costs.length==0||costs[0].length!=3) {
            return 0;
        }
        int n = costs.length;
        int[] dp = new int[3];
        for(int j=0;j<3;j++) {
            dp[j] = costs[0][j];
        }
        for(int i=1;i<n;i++) {
            int[] newDp = new int[3];
            newDp[0] = Math.min(dp[1], dp[2])+costs[i][0];
            newDp[1] = Math.min(dp[0], dp[2])+costs[i][1];
            newDp[2] = Math.min(dp[0], dp[1])+costs[i][2];
            dp = newDp;
        }
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }

}
