package cn.xux.algorithm.leetcode.vip.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 317. 离建筑物最近的距离
 * 你是个房地产开发商，想要选择一片空地 建一栋大楼。
 * 你想把这栋大楼够造在一个距离周边设施都比较方便的地方，
 * 通过调研，你希望从它出发能在 最短的距离和 内抵达周边全部的建筑物。
 * 请你计算出这个最佳的选址到周边全部建筑物的 最短距离和。
 *
 * 注意：
 * 你只能通过向上、下、左、右四个方向上移动。
 * 给你一个由 0、1 和 2 组成的二维网格，其中：
 * 0 代表你可以自由通过和选择建造的空地
 * 1 代表你无法通行的建筑物
 * 2 代表你无法通行的障碍物
 * 示例：
 * 输入: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 输出: 7
 * 解析:
 * 给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
 * 由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点，故返回7。
 *
 * 注意：
 * 你会保证有至少一栋建筑物，如果无法按照上述规则返回建房地点，则请你返回 -1。
 */
public class ShortestDistanceFromAllBuildings {

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==0) {
                    ans = Math.min(ans, bfs(grid, new boolean[m][n], i, j));
                }
            }
        }
        return ans;
    }

    int ans = Integer.MAX_VALUE;
    int[][] steps = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    private int bfs(int[][] grid, boolean[][] visited, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        int sum = 0;
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            while(size-->0) {
                int[] curr = queue.poll();
                for(int[] step : steps) {
                    int ni = curr[0]+step[0];
                    int nj = curr[1]+step[1];
                    if(ni>=0&&ni<grid.length&&nj>=0&&nj<grid[0].length
                            &&!visited[ni][nj]&&grid[ni][nj]!=2) {
                        visited[ni][nj] = true;
                        if(grid[ni][nj]==0) {
                            queue.offer(new int[]{ni, nj});
                        }else {
                            sum += level;
                            if(sum>=ans) {
                                return sum;
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

}
