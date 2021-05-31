package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1878. 矩阵中最大的三个菱形和
 * 给你一个 m x n 的整数矩阵 grid 。
 * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。
 * 本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。
 * 下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标注在图中。
 * 注意，菱形可以是一个面积为 0 的区域，如上图中右下角的紫色菱形所示。
 * 请你按照 降序 返回 grid 中三个最大的 互不相同的菱形和 。
 * 如果不同的和少于三个，则将它们全部返回。
 *
 * 示例 1：
 * 输入：grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
 * 输出：[228,216,211]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：20 + 3 + 200 + 5 = 228
 * - 红色：200 + 2 + 10 + 4 = 216
 * - 绿色：5 + 200 + 4 + 2 = 211
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[20,9,8]
 * 解释：最大的三个菱形和如上图所示。
 * - 蓝色：4 + 2 + 6 + 8 = 20
 * - 红色：9 （右下角红色的面积为 0 的菱形）
 * - 绿色：8 （下方中央面积为 0 的菱形）
 *
 * 示例 3：
 * 输入：grid = [[7,7,7]]
 * 输出：[7]
 * 解释：所有三个可能的菱形和都相同，所以返回 [7] 。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 1 <= grid[i][j] <= 105
 */
public class GetBiggestThreeRhombusSumsInAGrid {

    public static void main(String[] args) {
        new GetBiggestThreeRhombusSumsInAGrid().getBiggestThree(
                /*new int[][]{
                        {3,4,5,1,3},
                        {3,3,4,2,3},
                        {20,30,200,40,10},
                        {1,5,5,4,1},
                        {4,3,2,2,5}
                }*/
                /*new int[][]{
                        {1,2,3},
                        {4,5,6},
                        {7,8,9}
                }*/
                new int[][]{
                        {7,7,7,5}
                }
        );
    }

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] tmp = new int[3];
        for(int len=1;len<=(Math.min(m,n)+1)/2;len++) {
            for(int i=0;i+2*(len-1)<m;i++) {
                for(int j=len-1;j+len-1<n;j++) {
                    int sum = grid[i][j]+(len==1?0:grid[i+2*(len-1)][j]);
                    for(int k=i+1;k<i+2*(len-1);k++) {
                        int a = Math.min(k-i,i+2*(len-1)-k);
                        sum += grid[k][j-a]+grid[k][j+a];
                    }
                    if(sum==tmp[0]||sum==tmp[1]||sum==tmp[2]) {
                        continue;
                    }
                    if(sum>tmp[0]) {
                        tmp[2] = tmp[1];
                        tmp[1] = tmp[0];
                        tmp[0] = sum;
                    }else if(sum>tmp[1]) {
                        tmp[2] = tmp[1];
                        tmp[1] = sum;
                    }else if(sum>tmp[2]) {
                        tmp[2] = sum;
                    }
                }
            }
        }
        int len = 3-(tmp[2]==0?1:0)-(tmp[1]==0?1:0);
        int[] ans = new int[len];
        for(int i=0;i<len;i++) {
            ans[i] = tmp[i];
        }
        return ans;
    }

}
