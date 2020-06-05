package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.UndirectedGraphNode;

import java.util.*;

/**
 * Clone Graph
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 */
public class CloneGraph {

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null) {
            return null;
        }
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.val, new ArrayList<UndirectedGraphNode>());
        queue.offer(node);
        map.put(node, newNode);
        while(!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            List<UndirectedGraphNode> neighbors = curr.neighbors;
            for(UndirectedGraphNode neighbor : neighbors) {
                if(map.containsKey(neighbor)) {
                    map.get(curr).neighbors.add(map.get(neighbor));
                }else {
                    UndirectedGraphNode newNeighbor = new UndirectedGraphNode(neighbor.val,  new ArrayList<UndirectedGraphNode>());
                    map.put(neighbor, newNeighbor);
                    map.get(curr).neighbors.add(newNeighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return newNode;
    }


}
