package cn.xux.algorithm.leetcode.vip.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 305. 岛屿数量 II
 * 假设你设计一个游戏，用一个 m 行 n 列的 2D 网格来存储你的游戏地图。
 * 起始的时候，每个格子的地形都被默认标记为「水」。
 * 我们可以通过使用 addLand 进行操作，将位置 (row, col) 的「水」变成「陆地」。
 * 你将会被给定一个列表，来记录所有需要被操作的位置，然后你需要返回计算出来 每次 addLand 操作后岛屿的数量。
 * 注意：一个岛的定义是被「水」包围的「陆地」，通过水平方向或者垂直方向上相邻的陆地连接而成。
 * 你可以假设地图网格的四边均被无边无际的「水」所包围。
 * 请仔细阅读下方示例与解析，更加深入了解岛屿的判定。
 *
 * 示例:
 * 输入: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
 * 输出: [1,1,2,3]
 * 解析:
 * 起初，二维网格 grid 被全部注入「水」。（0 代表「水」，1 代表「陆地」）
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * 操作 #1：addLand(0, 0) 将 grid[0][0] 的水变为陆地。
 * 1 0 0
 * 0 0 0 Number of islands = 1
 * 0 0 0
 * 操作 #2：addLand(0, 1) 将 grid[0][1] 的水变为陆地。
 * 1 1 0
 * 0 0 0 岛屿的数量为 1
 * 0 0 0
 * 操作 #3：addLand(1, 2) 将 grid[1][2] 的水变为陆地。
 * 1 1 0
 * 0 0 1 岛屿的数量为 2
 * 0 0 0
 * 操作 #4：addLand(2, 1) 将 grid[2][1] 的水变为陆地。
 * 1 1 0
 * 0 0 1 岛屿的数量为 3
 * 0 1 0
 * 拓展：
 * 你是否能在 O(k log mn) 的时间复杂度程度内完成每次的计算？（k 表示 positions 的长度）
 */
public class NumberOfIslandsII {

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        if(m<1||n<1) {
            return ans;
        }
        int[] parent = new int[m*n];
        int[][] map = new int[m][n];
        int[][] steps = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int pre = 0;
        for(int[] pos : positions) {
            int x = pos[0];
            int y = pos[1];
            if(map[x][y]!=1) {
                map[x][y] = 1;
                Set<Integer> set = new HashSet<>();
                for(int[] step : steps) {
                    int nx = x+step[0];
                    int ny = y+step[1];
                    if(nx>=0&&nx<m&&ny>=0&&ny<n&&map[nx][ny]==1) {
                        set.add(parent[nx*m+ny]);
                        union(x*m+y, nx*m+ny, parent);

                    }
                }
                pre += 1-set.size();
            }
            ans.add(pre);
        }
        return ans;
    }

    private int find(int x, int[] parent) {
        if(parent[x]!=x) {
            return parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    private void union(int x, int y, int[] parent) {
        parent[find(y, parent)] = find(x, parent);
    }

}
