package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

/**
 * Given a binary tree and a sum,
 * determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 * Note: A leaf is a node with no children.
 * Example:
 * Given the below binary tree and sum = 22,
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) {
            return false;
        }
        return helper(root, sum);
    }

    public boolean helper(TreeNode node, int sum) {
        if(node.left==null&&node.right==null) {
            return sum==node.val;
        }
        boolean res = false;
        if(node.left!=null) {
            res |= helper(node.left, sum-node.val);
        }
        if(node.right!=null) {
            res |= helper(node.right, sum-node.val);
        }
        return res;
    }

}
