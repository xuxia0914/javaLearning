package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * 二叉树的直径
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longestpath between any two nodes in a tree.
 * This path may or may not pass through the root.
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {

    public static int result = 0;

    public static int solution(BinaryTreeNode tree) {
        dfs(tree);
        return result;
    }

    public static int dfs(BinaryTreeNode tree) {
        if(tree==null) {
            return 0;
        }
        int leftDfs = dfs(tree.left);
        int rightDfs = dfs(tree.right);
        result = Math.max(leftDfs+rightDfs, result);
        return 1+Math.max(leftDfs, rightDfs);
    }

    public static void main(String[] args) {
        BinaryTreeNode tree = new BinaryTreeNode(1);
        BinaryTreeNode node1 = new BinaryTreeNode(2);
        BinaryTreeNode node2 = new BinaryTreeNode(3);
        BinaryTreeNode node3 = new BinaryTreeNode(4);
        BinaryTreeNode node4 = new BinaryTreeNode(5);
        node1.left = node3;
        node1.right = node4;
        tree.left = node1;
        tree.right = node2;
        System.out.println(solution(tree));
    }

}
