package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

/**
 * 543. 二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过根结点。
 * 示例 : 给定二叉树
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class DiameterOfBinaryTree {

    int result;

    public int diameterOfBinaryTree(TreeNode root) {
        result = 1;
        depth(root);
        return result-1;
    }

    public int depth(TreeNode node) {
        if(node==null) {
            return 0;
        }
        int left = depth(node.left);
        int right = depth(node.right);
        result = Math.max(result, left+right+1);
        return Math.max(left, right)+1;
    }

}
