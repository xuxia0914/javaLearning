package cn.leetcode.xux.general.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 407. 接雨水 II
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，
 * 代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 * 示例：
 * 给出如下 3x6 的高度图:
 * [
 *   [1,4,3,1,3,2],
 *   [3,2,1,3,2,4],
 *   [2,3,3,2,3,1]
 * ]
 * 返回 4 。
 * 如上图所示，这是下雨前的高度图[[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] 的状态。
 * 下雨后，雨水将会被存储在这些方块中。总的接雨水量是4。
 *
 * 提示：
 * 1 <= m, n <= 110
 * 0 <= heightMap[i][j] <= 20000
 */
public class TrappingRainWaterII {

    public static void main(String[] args) {
        System.out.println(new TrappingRainWaterII().trapRainWater(new int[][]{
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        }));
    }

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;
        if(m<3||n<3) {
            return 0;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o->(o[2])));
        boolean[][] visited = new boolean[m][n];
        for(int j=0;j<n;j++) {
            queue.offer(new int[]{0, j, heightMap[0][j]});
            visited[0][j] = true;
            queue.offer(new int[]{m-1, j, heightMap[m-1][j]});
            visited[m-1][j] = true;
        }
        for(int i=1;i<m-1;i++) {
            queue.offer(new int[]{i, 0, heightMap[i][0]});
            visited[i][0] = true;
            queue.offer(new int[]{i, n-1, heightMap[i][n-1]});
            visited[i][n-1] = true;
        }
        int  ans = 0;
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int[] neis = new int[]{-1,0,1,0,-1};
            for(int i=0;i<4;i++) {
                int nx = curr[0]+neis[i];
                int ny = curr[1]+neis[i+1];
                if(nx>=0&&nx<m&&ny>=0&&ny<n&&!visited[nx][ny]) {
                    ans += Math.max(0, curr[2]-heightMap[nx][ny]);
                    queue.offer(new int[]{nx, ny, Math.max(curr[2], heightMap[nx][ny])});
                    visited[nx][ny] = true;
                }
            }
        }
        return ans;
    }

}
