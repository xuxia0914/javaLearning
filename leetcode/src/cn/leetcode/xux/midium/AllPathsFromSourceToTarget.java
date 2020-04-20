package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 797. 所有可能的路径
 * 给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点
 * （译者注：有向图是有方向的，即规定了a→b你就不能从b→a）空就是没有下一个结点了。
 *
 * 示例:
 * 输入: [[1,2], [3], [3], []]
 * 输出: [[0,1,3],[0,2,3]]
 * 解释: 图是这样的:
 * 0--->1
 * |    |
 * v    v
 * 2--->3
 * 这有两条路: 0 -> 1 -> 3 和 0 -> 2 -> 3.
 *
 * 提示:
 * 结点的数量会在范围 [2, 15] 内。
 * 你可以把路径以任意顺序输出，但在路径内的结点的顺序必须保证。
 */
public class AllPathsFromSourceToTarget {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        result.clear();
        if(graph==null||graph.length==0) {
            return result;
        }
        List<Integer> init = new ArrayList<>();
        init.add(0);
        dfs(graph, graph.length, 0, init);
        return result;
    }

    public void dfs(int[][] graph, int n, int idx, List<Integer> curr) {
        if(idx==n-1) {
            result.add(curr);
        }
        for(int next : graph[idx]) {
            List<Integer> list = new ArrayList<>(curr);
            list.add(next);
            dfs(graph, n, next, list);
        }
    }

}
