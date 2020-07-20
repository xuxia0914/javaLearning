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
public class Lintcode423 {
    public List<List<Integer>> connectedSet(ArrayList<DirectedGraphNode> nodes) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if(nodes==null||nodes.size()==0) {
            return  ans;
        }
        Map<DirectedGraphNode, Set<DirectedGraphNode>> map = new HashMap<>();
        for(DirectedGraphNode node : nodes) {
            if(!map.containsKey(node)) {
                map.put(node, new HashSet<>());
            }
            for(DirectedGraphNode nei : node.neighbors) {
                map.get(node).add(nei);
                if(!map.containsKey(nei)) {
                    map.put(nei, new HashSet<>());
                }
                map.get(nei).add(node);
            }
        }

        Set<DirectedGraphNode> visited = new HashSet<>();
        for(DirectedGraphNode node : nodes) {
            if(visited.add(node)) {
                Queue<DirectedGraphNode> queue = new LinkedList<>();
                queue.offer(node);
                List<Integer> list = new ArrayList<>();
                list.add(node.label);
                while(!queue.isEmpty()) {
                    DirectedGraphNode curr = queue.poll();
                    for(DirectedGraphNode nei : map.get(curr)) {
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

//  Definition for Directed graph.
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
}