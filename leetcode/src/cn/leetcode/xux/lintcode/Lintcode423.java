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
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        // write your code here
        List<List<Integer>> ans = new ArrayList<>();
        if(nodes==null||nodes.size()==0) {
            return  ans;
        }
        int n = nodes.size();
        DSU1 dsu = new DSU1(n+1);
        for(DirectedGraphNode node : nodes) {
            for(DirectedGraphNode nei : node.neighbors) {
                dsu.union(node.label, nei.label);
            }
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=1;i<=n;i++) {
            int group = dsu.find(i);
            if(!map.containsKey(group)) {
                map.put(group, new ArrayList<>());
            }
            map.get(group).add(i);
        }
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> value = entry.getValue();
            Collections.sort(value);
            ans.add(value);
        }
        return ans;
    }

}

class DSU1 {

    int[] parent;

    DSU1(int n) {
        parent = new int[n];
    }

    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        parent[find(y)] = find(x);
    }

}

//  Definition for Directed graph.
class DirectedGraphNode {
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
}
