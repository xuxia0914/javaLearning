package cn.xux.algorithm.common;

import java.util.List;

/**
 * 多元树
 */
public class NAryTreeNode {

    public int val;
    public List<NAryTreeNode> children;

    public NAryTreeNode() {}

    public NAryTreeNode(int _val,List<NAryTreeNode> _children) {
        val = _val;
        children = _children;
    }

}
