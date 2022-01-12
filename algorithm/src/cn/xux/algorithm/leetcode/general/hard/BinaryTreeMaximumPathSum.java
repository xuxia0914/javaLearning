package cn.xux.algorithm.leetcode.general.hard;

import cn.xux.algorithm.common.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。
 * 该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 示例 1:
 * 输入: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * 输出: 6
 * <p>
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 * <p>
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出: 42
 */
public class BinaryTreeMaximumPathSum {

    int result;

    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        helper(root);
        return result;
    }

    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        int sum = root.val;
        sum += Math.max(left, 0);
        sum += Math.max(right, 0);
        result = Math.max(result, sum);
        return Math.max(Math.max(left, right), 0) + root.val;
    }

}
