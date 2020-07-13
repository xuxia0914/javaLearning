package cn.leetcode.xux.general.midium;

import javafx.util.Pair;

import java.util.*;

/**
 * 1514. 概率最大的路径
 * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，
 * 其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
 * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
 * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
 *
 * 示例 1：
 * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * 输出：0.25000
 * 解释：从起点到终点有两条路径，其中一条的成功概率为 0.2 ，而另一条为 0.5 * 0.5 = 0.25
 *
 * 示例 2：
 * 输入：n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * 输出：0.30000
 *
 * 示例 3：
 * 输入：n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * 输出：0.00000
 * 解释：节点 0 和 节点 2 之间不存在路径
 *
 * 提示：
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * 每两个节点之间最多有一条边
 */
public class PathWithMaximumProbability {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        if(edges==null||edges.length==0) {
            return 0;
        }
        List<Pair<Integer, Double>>[] graph = new List[n];
        for(int i=0;i<edges.length;i++) {
            if(graph[edges[i][0]]==null) {
                graph[edges[i][0]] = new ArrayList<>();
            }
            graph[edges[i][0]].add(new Pair(edges[i][1], succProb[i]));
            if(graph[edges[i][1]]==null) {
                graph[edges[i][1]] = new ArrayList<>();
            }
            graph[edges[i][1]].add(new Pair(edges[i][0], succProb[i]));
        }
        Queue<Pair<Integer, Double>> queue = new LinkedList<>();
        queue.offer(new Pair(start, 1d));
        Map<Integer, Double> visited = new HashMap<>();
        visited.put(start, 1d);
        double ans = 0d;
        while(!queue.isEmpty()) {
            Pair<Integer, Double> currPair = queue.poll();
            int currNode = currPair.getKey();
            double currProb = currPair.getValue();
            if(graph[currNode]!=null) {
                for(Pair<Integer, Double> pair : graph[currNode]) {
                    int key = pair.getKey();
                    double nextValue = currProb*pair.getValue();
                    if(key==end) {
                        ans = Math.max(ans, nextValue);
                    }else if(nextValue>visited.getOrDefault(key, 0d)&&nextValue>ans) {
                        visited.put(key, nextValue);
                        queue.offer(new Pair<>(key, nextValue));
                    }
                }
            }
        }
        return ans;
    }

}
