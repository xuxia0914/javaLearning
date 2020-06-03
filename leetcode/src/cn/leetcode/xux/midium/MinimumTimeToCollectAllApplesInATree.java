package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 1443. 收集树上所有苹果的最少时间
 * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。
 * 通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，
 * 可以收集到所有苹果，并回到节点 0 。
 * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，
 * 表示有一条边连接 from 和 toi 。除此以外，还有一个布尔数组 hasApple ，
 * 其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
 *
 * 示例 1：
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]],
 * hasApple = [false,false,true,false,true,true,false]
 * 输出：8
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 *
 * 示例 2：
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]],
 * hasApple = [false,false,true,false,false,true,false]
 * 输出：6
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 *
 * 示例 3：
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]],
 * hasApple = [false,false,false,false,false,false,false]
 * 输出：0
 *
 * 提示：
 * 1 <= n <= 10^5
 * edges.length == n-1
 * edges[i].length == 2
 * 0 <= fromi, toi <= n-1
 * fromi < toi
 * hasApple.length == n
 */
public class MinimumTimeToCollectAllApplesInATree {

    public static void main(String[] args) {
        //8
        //{{0,1},{1,2},{2,3},{1,4},{2,5},{2,6},{4,7}}
        //[true,true,false,true,false,true,true,false]
        List<Boolean> hasApple = new ArrayList<>();
        hasApple.add(true);
        hasApple.add(true);
        hasApple.add(false);
        hasApple.add(true);
        hasApple.add(false);
        hasApple.add(true);
        hasApple.add(true);
        hasApple.add(false);
        System.out.println(new MinimumTimeToCollectAllApplesInATree().minTime(8,
                new int[][]{{0,1},{1,2},{2,3},{1,4},{2,5},{2,6},{4,7}},
                hasApple));
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Integer>[] graph = new List[n];
        for(int[] edge : edges) {
            if(graph[edge[0]]==null) {
                graph[edge[0]] = new ArrayList<>();
            }
            graph[edge[0]].add(edge[1]);
            if(graph[edge[1]]==null) {
                graph[edge[1]] = new ArrayList<>();
            }
            graph[edge[1]].add(edge[0]);
        }
        int[] ans = dfs(0, -1, graph, hasApple);
        return ans[1];
    }

    // res[0] 从当前节点开始向后遍历有无苹果，1 有， 0 无
    // res[1] 从当前节点向后遍历完所有苹果并返回该节点所需时间
    private int[] dfs(int curr, int pre, List<Integer>[] graph, List<Boolean> hasApple) {
        int[] ans = new int[2];
        ans[0] = hasApple.get(curr)?1:0;
        for(int nei : graph[curr]) {
            if(nei!=pre) {
                int[] neiAns = dfs(nei, curr, graph, hasApple);
                if(neiAns[0]==1) {
                    ans[0] = 1;
                    ans[1] += neiAns[1]+2;
                }
            }
        }
        return ans;
    }

}
