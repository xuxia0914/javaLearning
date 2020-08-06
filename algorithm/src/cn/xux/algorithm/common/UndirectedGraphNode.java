package cn.xux.algorithm.common;

import java.util.List;

public class UndirectedGraphNode {

    public int val;
    public List<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int x, List<UndirectedGraphNode> neighbors) {
        val = x;
        neighbors = neighbors;
    }

}