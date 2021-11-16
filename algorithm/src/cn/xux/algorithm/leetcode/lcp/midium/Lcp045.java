package cn.xux.algorithm.leetcode.lcp.midium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LCP 45. 自行车炫技赛场
 * 「力扣挑战赛」中 N*M 大小的自行车炫技赛场的场地由一片连绵起伏的上下坡组成，
 * 场地的高度值记录于二维数组 terrain 中，
 * 场地的减速值记录于二维数组 obstacle 中。
 * <p>
 * 若选手骑着自行车从高度为 h1 且减速值为 o1 的位置
 * 到高度为 h2 且减速值为 o2 的相邻位置（上下左右四个方向），
 * 速度变化值为 h1-h2-o2（负值减速，正值增速）。
 * 选手初始位于坐标 position 处且初始速度为 1，
 * 请问选手可以刚好到其他哪些位置时速度依旧为 1。
 * 请以二维数组形式返回这些位置。
 * 若有多个位置则按行坐标升序排列，
 * 若有多个位置行坐标相同则按列坐标升序排列。
 * <p>
 * 注意： 骑行过程中速度不能为零或负值
 * <p>
 * 示例 1：
 * <p>
 * 输入：position = [0,0], terrain = [[0,0],[0,0]], obstacle = [[0,0],[0,0]]
 * <p>
 * 输出：[[0,1],[1,0],[1,1]]
 * <p>
 * 解释：
 * 由于当前场地属于平地，根据上面的规则，选手从[0,0]的位置出发都能刚好在其他处的位置速度为 1。
 * <p>
 * 示例 2：
 * <p>
 * 输入：position = [1,1], terrain = [[5,0],[0,6]], obstacle = [[0,6],[7,0]]
 * <p>
 * 输出：[[0,1]]
 * <p>
 * 解释：
 * 选手从 [1,1] 处的位置出发，到 [0,1] 处的位置时恰好速度为 1。
 * <p>
 * 提示：
 * <p>
 * n == terrain.length == obstacle.length
 * m == terrain[i].length == obstacle[i].length
 * 1 <= n <= 100
 * 1 <= m <= 100
 * 0 <= terrain[i][j], obstacle[i][j] <= 100
 * position.length == 2
 * 0 <= position[0] < n
 * 0 <= position[1] < m
 */
public class Lcp045 {

    public static void main(String[] args) {
        int[][] ans = new Lcp045().bicycleYard(
                new int[]{2, 0},
                new int[][]{{0}, {5}, {10}},
                new int[][]{{4}, {3}, {0}}
        );
        System.out.println(ans);
    }

    int[][] ter;
    int[][] obs;
    int m;
    int n;
    boolean[][][] visited;
    PriorityQueue<int[]> queue;
    int[] start;

    public int[][] bicycleYard(int[] position, int[][] terrain, int[][] obstacle) {
        this.start = position;
        this.ter = terrain;
        this.obs = obstacle;
        this.m = terrain.length;
        this.n = terrain[0].length;
        visited = new boolean[m][n][102];
        queue = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int energy = 1 + ter[position[0]][position[1]];
        dfs(new int[]{position[0] - 1, position[1]}, energy);
        dfs(new int[]{position[0] + 1, position[1]}, energy);
        dfs(new int[]{position[0], position[1] - 1}, energy);
        dfs(new int[]{position[0], position[1] + 1}, energy);
        int[][] ans = new int[queue.size()][];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }

    // pos表示当前位置，v表示到达该位置之前的 速度+高度
    private void dfs(int[] pos, int v) {
        int x = pos[0];
        int y = pos[1];
        if (x < 0 || x >= m || y < 0 || y >= n ||
                v - obs[x][y] - ter[x][y] < 1 || visited[x][y][v]) {
            return;
        }
        visited[x][y][v] = true;
        int curr = v - obs[x][y];
        if (curr - ter[x][y] == 1 && !(x == start[0] && y == start[1])) {
            queue.offer(pos);
        }
        dfs(new int[]{x - 1, y}, curr);
        dfs(new int[]{x + 1, y}, curr);
        dfs(new int[]{x, y - 1}, curr);
        dfs(new int[]{x, y + 1}, curr);
    }

}
