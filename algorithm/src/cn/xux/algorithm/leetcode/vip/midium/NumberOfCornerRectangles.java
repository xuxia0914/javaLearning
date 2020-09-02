package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 750.角矩形的数量
 * 给定一个只包含 0 和 1 的网格，找出其中角矩形的数量。
 * 一个 角矩形是由四个不同的在网格上的 1 形成的轴对称的矩形。
 * 注意只有角的位置才需要为 1。并且，4 个 1 需要是不同的。
 *
 * 示例 1：
 * 输入：grid =
 * [[1, 0, 0, 1, 0],
 *  [0, 0, 1, 0, 1],
 *  [0, 0, 0, 1, 0],
 *  [1, 0, 1, 0, 1]]
 * 输出：1
 * 解释：只有一个角矩形，角的位置为 grid[1][2], grid[1][4], grid[3][2], grid[3][4]。
 *
 * 示例 2：
 * 输入：grid =
 * [[1, 1, 1],
 * [1, 1, 1],
 * [1, 1, 1]]
 * 输出：9
 * 解释：这里有 4 个 2x2 的矩形，4 个 2x3 和 3x2 的矩形和 1 个 3x3 的矩形。
 *
 * 示例 3：
 * 输入：grid =
 * [[1, 1, 1, 1]]
 * 输出：0
 * 解释：矩形必须有 4 个不同的角。
 *
 * 注：
 * 网格 grid 中行和列的数目范围为 [1, 200]。
 * Each grid[i][j] will be either 0 or 1.
 * 网格中 1 的个数不会超过 6000。
 */
public class NumberOfCornerRectangles {

    public int countCornerRectangles(int[][] grid) {
        // Write your code here
        if(grid==null||grid.length<2||grid[0].length<2) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for(int r1=0;r1<m-1;r1++) {
            for(int r2=r1+1;r2<m;r2++) {
                int cnt = 0;
                for(int c=0;c<n;c++) {
                    if(grid[r1][c]==1&&grid[r2][c]==1) {
                        cnt++;
                    }
                }
                ans += cnt*(cnt-1)/2;
            }
        }
        return ans;
    }

    public int countCornerRectangles1(int[][] grid) {
        // Write your code here
        if(grid==null||grid.length<2||grid[0].length<2) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int ans = 0;
        for(int i=0;i<m-1;i++) {
            for(int j=0;j<n-1;j++) {
                if(grid[i][j]==1) {
                    for(int dri=i+1;i<m;i++) {
                        for(int drj=j+1;j<n;j++) {
                            if(grid[dri][drj]==1&&grid[i][drj]==1&&grid[dri][j]==1) {
                                ans++;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

}
