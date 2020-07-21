package cn.leetcode.xux.lintcode;

import java.util.*;

/**
 * 432. 找出有向图中的弱连通分量
 * 中文English
 * 请找出有向图中弱连通分量。图中的每个节点包含 1 个标签和1 个相邻节点列表。（有向图的弱连通分量是任意两点均有有向边相连的极大子图）
 *
 * 样例
 * 样例 1:
 *
 * 输入: {1,2,4#2,4#3,5#4#5#6,5}
 * 输出: [[1,2,4],[3,5,6]]
 * 解释:
 *   1----->2    3-->5
 *    \     |        ^
 *     \    |        |
 *      \   |        6
 *       \  v
 *        ->4
 * 样例 2:
 *
 * 输入: {1,2#2,3#3,1}
 * 输出: [[1,2,3]]
 * 说明
 * 图模型说明:
 * https://www.lintcode.com/help/graph
 *
 * 注意事项
 * 将连通分量内的元素升序排列。
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
            return ans;
        }
        int n = nodes.size();
        Map<Integer, Integer> mapping = new HashMap<>();
        for(int i=0;i<n;i++) {
            mapping.put(nodes.get(i).label, i);
        }
        DSU dsu = new DSU(n);
        for(UndirectedGraphNode node : nodes) {
            for(UndirectedGraphNode nei : node.neighbors) {
                dsu.union(mapping.get(node.label), mapping.get(nei.label));
            }
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(UndirectedGraphNode node : nodes) {
            int key = dsu.find(mapping.get(node.label));
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(node.label);
        }
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            Collections.sort(list);
            ans.add(list);
        }
        return ans;
    }

}

//  Definition for Undirected graph.
class UndirectedGraphNode {
    int label;
    ArrayList<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
