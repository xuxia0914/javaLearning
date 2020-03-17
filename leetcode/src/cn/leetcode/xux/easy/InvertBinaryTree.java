package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
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
