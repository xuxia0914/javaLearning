package cn.leetcode.xux.general.hard;

import java.util.*;

/**
 * 1168. 水资源分配优化  显示英文描述
 * 村里面一共有 n 栋房子。我们希望通过建造水井和铺设管道来为所有房子供水。
 * 对于每个房子 i，我们有两种可选的供水方案：
 * 一种是直接在房子内建造水井，成本为 wells[i]；
 * 另一种是从另一口井铺设管道引水，数组 pipes 给出了在房子间铺设管道的成本，
 * 其中每个 pipes[i] = [house1, house2, cost] 代表用管道将 house1 和 house2 连接在一起的成本。
 * 当然，连接是双向的。
 *  * 请你帮忙计算为所有房子都供水的最低总成本。
 *
 * 示例：
 * 输入：n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * 输出：3
 * 解释：
 * 上图展示了铺设管道连接房屋的成本。
 * 最好的策略是在第一个房子里建造水井（成本为 1），然后将其他房子铺设管道连起来（成本为 2），所以总成本为 3。
 *
 * 提示：
 * 1 <= n <= 10000
 * wells.length == n
 * 0 <= wells[i] <= 10^5
 * 1 <= pipes.length <= 10000
 * 1 <= pipes[i][0], pipes[i][1] <= n
 * 0 <= pipes[i][2] <= 10^5
 * pipes[i][0] != pipes[i][1]
 */
public class OptimizeWaterDistributionInAVillage {

    //定义一个0号房子，与房子i的成本是wells[i]
    //prim(普里姆)算法
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        List<int[]>[] graph = new List[n+1];
        graph[0] = new ArrayList<>();
        for(int i=0;i<n;i++) {
            graph[0].add(new int[]{i+1, wells[i]});
            graph[i+1] = new ArrayList<>();
        }
        for(int[] pipe : pipes) {
            int i = pipe[0], j = pipe[1], v = pipe[2];
            graph[i].add(new int[]{j, v});
            graph[j].add(new int[]{i, v});
        }
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int[] nei : graph[curr]) {
                if(costs[nei[0]]>nei[1]) {
                    costs[nei[0]] = nei[1];
                    queue.offer(nei[0]);
                }
            }
        }
        int ans = 0;
        for(int cost : costs) {
            ans += cost;
        }
        return ans;
    }

    //kruskal(克鲁斯卡尔)算法
    public int minCostToSupplyWater1(int n, int[] wells, int[][] pipes) {
        int[][] costs = new int[pipes.length+n][3];
        int idx = 0;
        for(int i=0;i<n;i++) {
            costs[idx++] = new int[]{0, i+1, wells[i]};
        }
        for(int i=0;i<pipes.length;i++) {
            costs[idx++] = pipes[i];
        }
        Arrays.sort(costs, Comparator.comparingInt(o->o[2]));
        int[] parent = new int[n+1];
        for(int i=1;i<=n;i++) {
            parent[i] = i;
        }
        int ans = 0;
        for(int[] cost : costs) {
            int p1 = find(parent, cost[0]);
            int p2 = find(parent, cost[1]);
            if(p1!=p2) {
                ans += cost[2];
                parent[p1] = p2;
            }
        }
        return ans;
    }

    private int find(int[] parent, int idx) {
        while(parent[idx]!=idx) {
            parent[idx] = find(parent, parent[idx]);
        }
        return parent[idx];
    }

}
