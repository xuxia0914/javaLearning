package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 707. 最优账户结余
 * 中文English
 * 给一有向图,每一条边用一个 三元组 表示,
 * 比如 [u, v, w] 代表权值为 w 的从 u 到 v 的一条边.
 * 计算出保证每个点的权重相等需要添加的最少的边数.
 * 也就是说, 指向这一点的边权重总和等于这个点指向其他点的边权重之和.
 *
 * 样例
 * 样例1
 *
 * 输入: [[0,1,10],[2,0,5]]
 * 输出: 2
 * 说明:
 * 需要添加两条边
 * 它们是 [1,0,5] 以及 [1,2,5]
 * 样例2
 *
 * 输入: [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 * 输出: 1
 * 说明:
 * 只需要添加一条边 [1,0,4]
 * 注意事项
 * 注意 u ≠ v 且 w > 0
 * 下标不一定是线性的, 比如 顶点下标可以为 0,1,2, 也可以为 0,2,6.
 */
public class Lintcode707 {

    /*
     * @param edges: a directed graph where each edge is represented by a tuple
     * @return: the number of edges
     */
    public int balanceGraph(int[][] edges) {
        // Write your code here
        if(edges==null||edges.length==0) {
            return 0;
        }
        Map<Integer, Integer> heavy = new HashMap<>();
        for(int[] e : edges) {
            heavy.put(e[0], heavy.getOrDefault(e[0], 0)-e[2]);
            heavy.put(e[1], heavy.getOrDefault(e[1], 0)+e[2]);
        }
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : heavy.entrySet()) {
            int value = entry.getValue();
            if(value>0) {
                pos.add(value);
            }else if(value<0) {
                neg.add(-value);
            }
        }
        if(pos.size()==0) {
            return 0;
        }
        

    }

}
