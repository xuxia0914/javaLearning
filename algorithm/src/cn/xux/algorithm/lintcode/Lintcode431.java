package cn.xux.algorithm.lintcode;

import java.util.*;

/**
 * 431. 找无向图的连通块
 * 中文English
 * 找出无向图中所有的连通块。
 *
 * 图中的每个节点包含一个label属性和一个邻接点的列表。
 *
 * （一个无向图的连通块是一个子图，其中任意两个顶点通过路径相连，且不与整个图中的其它顶点相连。）
 *
 * 你需要返回 label 集合的列表.
 *
 * 样例
 * 样例 1:
 *
 * 输入: {1,2,4#2,1,4#3,5#4,1,2#5,3}
 * 输出: [[1,2,4],[3,5]]
 * 解释:
 *
 *   1------2  3
 *    \     |  |
 *     \    |  |
 *      \   |  |
 *       \  |  |
 *         4   5
 * 样例 2:
 *
 * 输入: {1,2#2,1}
 * 输出: [[1,2]]
 * 解释:
 *
 *   1--2
 *
 * 说明
 * 关于图的表示
 *
 * 注意事项
 * 每个连通块内部应该按照label属性升序排序. 不同的连通块之间可以是任意顺序.
 */
public class Lintcode431 {

    /*
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if(nodes==null||nodes.size()==0) {
            return  ans;
        }
        Map<UndirectedGraphNode, Set<UndirectedGraphNode>> map = new HashMap<>();
        for(UndirectedGraphNode node : nodes) {
            if(!map.containsKey(node)) {
                map.put(node, new HashSet<>());
            }
            for(UndirectedGraphNode nei : node.neighbors) {
                map.get(node).add(nei);
                if(!map.containsKey(nei)) {
                    map.put(nei, new HashSet<>());
                }
                map.get(nei).add(node);
            }
        }

        Set<UndirectedGraphNode> visited = new HashSet<>();
        for(UndirectedGraphNode node : nodes) {
            if(visited.add(node)) {
                Queue<UndirectedGraphNode> queue = new LinkedList<>();
                queue.offer(node);
                List<Integer> list = new ArrayList<>();
                list.add(node.label);
                while(!queue.isEmpty()) {
                    UndirectedGraphNode curr = queue.poll();
                    for(UndirectedGraphNode nei : map.get(curr)) {
                        visited.add(nei);
                        queue.offer(nei);
                        list.add(nei.label);
                    }
                }
                ans.add(list);
            }
        }
        return ans;
    }

}

//Definition for Undirected graph.
class UndirectedGraphNode {

    int label;
    ArrayList<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x; neighbors = new ArrayList<UndirectedGraphNode>();
    }

}
