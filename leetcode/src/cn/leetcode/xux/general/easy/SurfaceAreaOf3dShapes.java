package cn.leetcode.xux.general.easy;

/**
 * 892. 三维形体的表面积
 * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
 * 请你返回最终形体的表面积。
 *
 * 示例 1：
 * 输入：[[2]]
 * 输出：10
 *
 * 示例 2：
 * 输入：[[1,2],[3,4]]
 * 输出：34
 *
 * 示例 3：
 * 输入：[[1,0],[0,2]]
 * 输出：16
 *
 * 示例 4：
 * 输入：[[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 *
 * 示例 5：
 * 输入：[[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 *
 * 提示：
 * 1 <= N <= 50
 * 0 <= grid[i][j] <= 50
 */
public class SurfaceAreaOf3dShapes {

    public int surfaceArea(int[][] grid) {
        if(grid==null||grid.length==0||grid[0].length==0) {
            return 0;
        }
        int cnt = 0;
        int len = grid.length;
        int result = 0;
        for(int i=0;i<len;i++) {
            for(int j=0;j<len;j++) {
                if(grid[i][j]!=0) {
                    cnt++;
                    int forwardCover = i==0?0:Math.min(grid[i][j],grid[i-1][j]);
                    result += grid[i][j]-forwardCover;
                    int backCover = i==len-1?0:Math.min(grid[i][j], grid[i+1][j]);
                    result += grid[i][j]-backCover;
                    int leftCover = j==0?0:Math.min(grid[i][j],grid[i][j-1]);
                    result += grid[i][j]-leftCover;
                    int rightCover = j==len-1?0:Math.min(grid[i][j], grid[i][j+1]);
                    result += grid[i][j]-rightCover;
                }
            }
        }
        result += 2*cnt;
        return result;
    }

}
