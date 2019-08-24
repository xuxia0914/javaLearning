package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * Invert a binary tree.
 * Example:
 * Input:
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * Output:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class InvertBinaryTree {

    public BinaryTreeNode invertTree(BinaryTreeNode root) {
        if(root==null) {
            return null;
        }
        BinaryTreeNode right = invertTree(root.left);
        BinaryTreeNode left = invertTree(root.right);
        root.left = left;
        root.right = right;
        return root;
    }

}
