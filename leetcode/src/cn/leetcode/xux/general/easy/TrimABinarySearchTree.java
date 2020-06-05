package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.TreeNode;

/**
 * 669. 修剪二叉搜索树
 * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
 * 你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 示例 1:
 * 输入:
 *     1
 *    / \
 *   0   2
 *   L = 1
 *   R = 2
 * 输出:
 *     1
 *       \
 *        2
 *
 * 示例 2:
 * 输入:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *   L = 1
 *   R = 3
 * 输出:
 *       3
 *      /
 *    2
 *   /
 *  1
 *
 * 问题分析：
 * 此题考查BST的遍历问题，对于不满足条件的节点，我们省略即可。
 */
public class TrimABinarySearchTree {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root==null) {
            return null;
        }
        if(root.val<L) {
            return trimBST(root.right, L, R);
        }
        if(root.val==L) {
            root.left = null;
            root.right = trimBST(root.right, L, R);
        }
        if(root.val>L&&root.val<R) {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
        }
        if(root.val==R) {
            root.left = trimBST(root.left, L, R);
            root.right = null;
        }
        if(root.val>R) {
            return trimBST(root.left, L, R);
        }
        return root;
    }

}
