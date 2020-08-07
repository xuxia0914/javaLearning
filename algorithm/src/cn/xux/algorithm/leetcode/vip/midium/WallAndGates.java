package cn.xux.algorithm.leetcode.vip.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. 墙与门
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 *
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。
 * 你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
 */
public class WallAndGates {

    public void wallsAndGates(int[][] rooms) {
        if(rooms==null||rooms.length==0||rooms[0].length==0) {
            return;
        }
        Queue<int[]> queue = new LinkedList<>();
        int m = rooms.length;
        int n = rooms[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(rooms[i][j]==0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int[][] steps = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int[] step : steps) {
                int ni = curr[0]+step[0];
                int nj = curr[1]+step[1];
                if(ni>=0&&ni<m&&nj>=0&&nj<n&&rooms[ni][nj]==Integer.MAX_VALUE) {
                    rooms[ni][nj] = rooms[curr[0]][curr[1]]+1;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
    }

}
