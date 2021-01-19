package cn.xux.algorithm.leetcode.general.midium;

import java.util.*;

/**
 * 1584. 连接所有点的最小费用
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：
 * |xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 *
 * 示例 1：
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 *
 * 解释：
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 *
 * 示例 2：
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 *
 * 示例 3：
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 *
 * 示例 4：
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 *
 * 示例 5：
 * 输入：points = [[0,0]]
 * 输出：0
 *
 * 提示：
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 */
public class MinCostToConnectAllPoints {

    public static void main(String[] args) {
        MinCostToConnectAllPoints mc = new MinCostToConnectAllPoints();
        System.out.println(mc.minCostConnectPoints1(
                new int[][]{{3,12},{-2,5},{-4,1}}));
    }

    // kruskal(克鲁斯卡尔)算法 适合于边比较稀疏的图
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] edges = new int[n*(n-1)/2][3];
        int idx = 0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                edges[idx][0] = i;
                edges[idx][1] = j;
                edges[idx][2] = Math.abs(points[i][0]-points[j][0])
                        + Math.abs(points[i][1]-points[j][1]);
                idx++;
            }
        }
        Arrays.sort(edges, Comparator.comparingInt(o->o[2]));
        int[] parent = new int[n];
        for(int i=1;i<n;i++) {
            parent[i] = i;
        }
        int ans = 0;
        for(int[] edge : edges) {
            int p1 = find(edge[0], parent);
            int p2 = find(edge[1], parent);
            if(p1!=p2) {
                ans += edge[2];
                union(edge[0], edge[1], parent);
            }
        }
        return ans;
    }

    private int find(int x, int[] parent) {
        if(parent[x]!=x) {
            return parent[x] = find(parent[x], parent);
        }
        return x;
    }

    private void union(int x, int y, int[] parent) {
        parent[find(y, parent)] = find(x, parent);
    }

    // prim(普里姆)算法 适合于边比较密集的图
    public int minCostConnectPoints1(int[][] points) {
        int n = points.length;
        int ans = 0;
        boolean[] visited = new boolean[n];
        visited[0] = true;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o->o[2]));
        for(int i=1;i<n;i++) {
            queue.offer(new int[]{0,i,Math.abs(points[0][0]
                    -points[i][0])+Math.abs(points[0][1]-points[i][1])});
        }
        while(queue.size()>0) {
            int[] curr = queue.poll();
            if(!visited[curr[1]]) {
                ans += curr[2];
                visited[curr[1]] = true;
                for(int i=0;i<n;i++) {
                    if(!visited[i]) {
                        queue.offer(new int[]{curr[1], i, Math.abs(points[curr[1]][0]-points[i][0])
                                +Math.abs(points[curr[1]][1]-points[i][1])});
                    }
                }
            }
        }
        return ans;
    }

}
