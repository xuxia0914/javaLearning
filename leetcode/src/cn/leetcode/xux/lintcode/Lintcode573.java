package cn.leetcode.xux.lintcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 573. 邮局的建立 II
 * 中文English
 * 给出一个二维的网格，每一格可以代表墙 2 ，房子 1，以及空 0 (用数字0,1,2来表示)，
 * 在网格中找到一个位置去建立邮局，使得所有的房子到邮局的距离和是最小的。
 * 返回所有房子到邮局的最小距离和，如果没有地方建立邮局，则返回-1.
 *
 * 样例
 * 样例 1:
 *
 * 输入：[[0,1,0,0,0],[1,0,0,2,1],[0,1,0,0,0]]
 * 输出：8
 * 解释： 在(1,1)处建立邮局，所有房子到邮局的距离和是最小的。
 * 样例 2:
 *
 * 输入：[[0,1,0],[1,0,1],[0,1,0]]
 * 输出：4
 * 解释：在(1,1)处建立邮局，所有房子到邮局的距离和是最小的。
 * 注意事项
 * 你不能穿过房子和墙，只能穿过空地。
 * 你只能在空地建立邮局。
 */
public class Lintcode573 {

    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public int shortestDistance(int[][] grid) {
        // write your code here
        if(grid==null||grid.length==0||grid[0].length==0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        int cnt1 = 0;
        for (int[] r : grid) {
            for (int c : r) {
                if (c == 1) {
                    cnt1++;
                }
            }
        }
        int ans = -1;
        int[][] steps = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==0) {
                    Queue<int[]> queue = new LinkedList<>();
                    Set<Integer> visited = new HashSet<>();
                    queue.offer(new int[]{i, j});
                    visited.add(i*n+j);
                    int level = 1;
                    int cnt = 0;
                    int sum = 0;
                    while(!queue.isEmpty()&&cnt<cnt1) {
                        int size = queue.size();
                        while(size-->0) {
                            int[] curr = queue.poll();
                            for(int[] step : steps) {
                                int[] nei = new int[]{curr[0]+step[0], curr[1]+step[1]};
                                if(nei[0]>=0&&nei[0]<m&&nei[1]>=0&&nei[1]<n
                                        &&grid[nei[0]][nei[1]]!=2&&visited.add(nei[0]*n+nei[1])) {
                                    if(grid[nei[0]][nei[1]]==1) {
                                        cnt++;
                                        sum += level;
                                    }else {
                                        queue.offer(nei);
                                    }
                                }
                            }
                        }
                        level++;
                    }
                    if(cnt==cnt1) {
                        ans = ans==-1?sum:Math.min(ans, sum);
                    }
                }
            }
        }
        return ans;
    }

}
