package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example 1:
 * Given the following tree [3,9,20,null,null,15,7]:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 * Example 2:
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Return false.
 */
public class BalancedBinaryTree {

    public boolean isBalanced(BinaryTreeNode root) {
        return helper(root)>=0;
    }

    public int helper(BinaryTreeNode root) {
        if(root==null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        if(left<0||right<0) {
            return -1;
        }
        if(Math.abs(left-right)>1) {
            return -1;
        }else {
            return 1+Math.max(left, right);
        }
    }

}
