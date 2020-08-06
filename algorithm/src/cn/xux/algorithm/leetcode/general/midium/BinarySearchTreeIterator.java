package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.Stack;

/**
 * 二叉搜索树迭代器
 * Implement an iterator over a binary search tree (BST).
 * Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
 * where h is the height of the tree.
 */
public class BinarySearchTreeIterator {

    private Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        this.stack = new Stack<TreeNode>();
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        TreeNode node = this.stack.pop();
        if(node.right != null) {
            TreeNode tmp = node.right;
            while(tmp != null) {
                this.stack.push(tmp);
                tmp = tmp.left;
            }
        }
        return node.val;
    }

}
