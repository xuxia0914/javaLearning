package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 1014. 打砖块
 * 中文English
 * 有一个由 1 和 0 组成的网格: 单元格中的 1 表示砖块. 当且仅当一个砖块直接连接到网格底部, 或者其四周相邻的砖块至少有一个不会掉落时, 这个砖块不会掉落.
 *
 * 我们将按顺序进行一些消除. 每次会指定一个位置 (i, j), 消除该位置上的砖块, 然后可能会有一些砖块因这次消除操作而掉落.
 *
 * 返回一个数组, 表示每次消除后会有多少砖块掉落.
 *
 * 样例
 * 样例 1:
 *
 * 输入: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * 输出: [2]
 * 解释: 消除 (1, 0) 处的砖块时, 位于 (1, 1) 和 (1, 2) 的砖块会掉落, 所以返回 2.
 * 样例 2:
 *
 * 输入: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * 输出: [0,0]
 * 解释: 当我们消除 (1, 0) 的砖块时, (1, 1) 的砖块已经在前一次操作中被消除了.
 * 样例 3:
 *
 * 输入: grid = [[1],[1],[1]], hits = [[0,0],[0,0]]
 * 输出: [2,0]
 * 解释: 第一次消除 (0, 0) 时, 另外两个砖块都掉落了. 第二次尝试消除 (0, 0) 时, 已经没有砖块了.
 * 说明
 * 你可以想象所有的砖块在同一个平面上. 网格底部的砖块与一堵墙连接着而保持不掉落. 如果一个砖块掉落, 它将会消失.
 *
 * 注意事项
 * 网格的行列数在 [1, 200] 的范围内.
 * 每次消除的位置不重复, 并且一定在网格范围内.
 * 如果要消除的位置没有砖块, 那么什么都不会发生.
 * 行下标越小则越低, 也就是说, 行下标为 0 的位置连接着网格底部.
 */
public class Lintcode1014 {

    /**
     * TODO
     * @param grid: a grid
     * @param hits: some erasures order
     * @return: an array representing the number of bricks that will drop after each erasure in sequence
     */
    public int[] hitBricks(int[][] grid, int[][] hits) {
        // Write your code here
        if(grid.length<2) {
            return new int[hits.length];
        }
        int m = grid.length;
        int n = grid[0].length;
        Map<Integer, List<Integer>> outDegree = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for(int i=1;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j]==1) {
                    if(grid[i-1][j]==1){
                        if(!outDegree.containsKey((i-1)*n+j)) {
                            outDegree.put((i-1)*n+j, new ArrayList<>());
                        }
                        outDegree.get((i-1)*n+j).add(i*n+j);
                        inDegree.put(i*n+j, inDegree.getOrDefault(i*n+j, 0)+1);
                    }
                    if(grid[i][j+1]==1) {
                        if(!outDegree.containsKey(i*n+j)) {
                            outDegree.put(i*n+j, new ArrayList<>());
                        }
                        outDegree.get(i*n+j).add((i+1)*n+j);
                        inDegree.put((i+1)*n+j, inDegree.getOrDefault((i+1)*n+j, 0)+1);
                        if(!outDegree.containsKey((i+1)*n+j)) {
                            outDegree.put((i+1)*n+j, new ArrayList<>());
                        }
                        outDegree.get((i+1)*n+j).add(i*n+j);
                        inDegree.put(i*n+j, inDegree.getOrDefault(i*n+j, 0)+1);
                    }
                }
            }
        }
        int[] ans = new int[hits.length];
        for(int i=0;i<hits.length;i++) {
            int[] curr = hits[i];
            int cnt = 0;
            if(grid[curr[0]][curr[1]]==1) {
                grid[curr[0]][curr[1]] = 0;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(curr[0]*n+curr[1]);
                while(!queue.isEmpty()) {
                    int key = queue.poll();
                    if(outDegree.containsKey(key)) {
                        for(int nei : outDegree.get(key)) {
                            if(inDegree.get(nei)==1) {
                                inDegree.remove(nei);
                                cnt++;
                                queue.offer(nei);
                            }else {
                                inDegree.put(nei, inDegree.get(nei)-1);
                            }
                        }
                    }
                }
            }
            ans[i] = cnt;
        }
        return ans;
    }

}
