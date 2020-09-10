package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1197：进击的骑士（超详细的解法！！！）
 * 一个坐标可以从 -infinity 延伸到 +infinity 的 无限大的 棋盘上，
 * 你的 骑士 驻扎在坐标为 [0, 0] 的方格里。
 * 骑士的走法和中国象棋中的马相似，走 “日” 字：
 * 即先向左（或右）走 1 格，再向上（或下）走 2 格；或先向左（或右）走 2 格，再向上（或下）走 1 格。
 * 每次移动，他都可以按图示八个方向之一前进。
 * 现在，骑士需要前去征服坐标为 [x, y] 的部落，请你为他规划路线。
 * 最后返回所需的最小移动次数即可。本题确保答案是一定存在的。
 *
 * 示例 1：
 * 输入：x = 2, y = 1
 * 输出：1
 * 解释：[0, 0] → [2, 1]
 *
 * 示例 2：
 * 输入：x = 5, y = 5
 * 输出：4
 * 解释：[0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
 *
 * 提示：
 * |x| + |y| <= 300
 */
public class MinimumKnightMoves {

    //bfs
    public int minKnightMoves(int x, int y) {
        if(x==0&&y==0) {
            return 0;
        }
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(new int[]{0, 0});
        set.add(0);
        int[][] steps = new int[][]{{1,2},{2,1},{-1,2},{2,-1},{-2,1},{1,-2},{-1,-2},{-2,-1}};
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                int[] curr = queue.poll();
                for(int[] step : steps) {
                    int nx = curr[0]+step[0];
                    int ny = curr[1]+step[1];
                    if(nx==x&&ny==y) {
                        return level+1;
                    }
                    int key = nx*1000+ny;
                    if(nx>-1&&ny>-1&&set.add(key)) {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            level++;
        }
        return -1;
    }

}
