package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.TreeNode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return helper(root)!=-1;
    }

    public int helper(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        return left==-1||right==-1||Math.abs(left-right)>1?-1:1+Math.max(left, right);
    }

}
