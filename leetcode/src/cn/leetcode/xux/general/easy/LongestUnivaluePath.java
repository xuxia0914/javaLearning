package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.TreeNode;

/**
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 * The length of path between two nodes is represented by the number of edges between them.
 * Example 1:
 * Input:
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * Output: 2
 * Example 2:
 * Input:
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 * Output: 2
 * Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
 */
public class LongestUnivaluePath {

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        max = 0;
        if(root==null) {
            return 0;
        }
        helper(root);
        return max;
    }

    public int helper(TreeNode node) {
        int len1;
        int len2;
        int curr = 0;
        int res = 0;
        if(node.left!=null) {
            len1 = helper(node.left);
            if(node.left.val==node.val) {
                curr += len1 + 1;
                res = Math.max(res, len1+1);
            }
        }
        if(node.right!=null) {
            len2 = helper(node.right);
            if(node.right.val==node.val) {
                curr += len2 + 1;
                res = Math.max(res, len2+1);
            }
        }
        max = Math.max(max, curr);
        return res;
    }

    public static void main(String[] args) {
        LongestUnivaluePath lup = new LongestUnivaluePath();
        System.out.println(lup.longestUnivaluePath(new TreeNode(new Integer[]{5,4,5,1,1,5})));
        System.out.println(lup.longestUnivaluePath(new TreeNode(new Integer[]{1,4,5,4,4,5})));
    }

}
