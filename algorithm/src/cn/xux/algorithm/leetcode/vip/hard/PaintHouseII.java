package cn.xux.algorithm.leetcode.vip.hard;

/**
 * 265. 粉刷房子 II
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，
 * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；
 * costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。
 * 请你计算出粉刷完所有房子最少的花费成本。注意：所有花费均为正整数。
 *
 * 输入: [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
 *          或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
 *
 * 进阶：您能否在 O(nk) 的时间复杂度下解决此问题？
 */
public class PaintHouseII {

    public int minCostII(int[][] costs) {
        if(costs==null||costs.length==0||costs[0].length<2) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        Integer min1 = null;
        Integer min1Idx = null;
        Integer min2 = null;
        for(int j=0;j<k;j++) {
            if(min1==null||costs[0][j]<min1) {
                min2 = min1;
                min1 = costs[0][j];
                min1Idx = j;
            }else if(min2==null||costs[0][j]<min2) {
                min2 = costs[0][j];
            }
        }
        for(int i=1;i<n;i++) {
            Integer newMin1 = null;
            Integer newMin1Idx = null;
            Integer newMin2 = null;
            for(int j=0;j<k;j++) {
                int curr = ((j==min1Idx)?min2:min1)+costs[i][j];
                if(newMin1==null||curr<newMin1) {
                    newMin2 = newMin1;
                    newMin1 = curr;
                    newMin1Idx = j;
                }else if(newMin2==null||curr<newMin2) {
                    newMin2 = curr;
                }
            }
            min1 = newMin1;
            min1Idx = newMin1Idx;
            min2 = newMin2;
        }
        return min1;
    }

}
