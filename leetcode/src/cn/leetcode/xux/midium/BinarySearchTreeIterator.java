package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

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

    private Stack<BinaryTreeNode> stack;

    public BinarySearchTreeIterator(BinaryTreeNode root) {
        this.stack = new Stack<BinaryTreeNode>();
        while (root != null) {
            this.stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        BinaryTreeNode node = this.stack.pop();
        if(node.right != null) {
            BinaryTreeNode tmp = node.right;
            while(tmp != null) {
                this.stack.push(tmp);
                tmp = tmp.left;
            }
        }
        return node.val;
    }

}
