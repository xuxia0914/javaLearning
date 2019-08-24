package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * Given a binary search tree and the lowest and highest boundaries as L and R,
 * trim the tree so that all its elements lies in [L, R] (R >= L).
 * You might need to change the root of the tree,
 * so the result should return the new root of the trimmed binary search tree.
 * 示例:
 * Input:
 *     1
 *    / \
 *   0   2
 *   L = 1
 *   R = 2
 * Output:
 *     1
 *       \
 *        2
 * Input:
 *     3
 *    / \
 *   0   4
 *    \
 *     2
 *    /
 *   1
 *   L = 1
 *   R = 3
 * Output:
 *       3
 *      /
 *    2
 *   /
 *  1
 * 问题分析：
 * 此题考查BST的遍历问题，对于不满足条件的节点，我们省略即可。
 */
public class TrimABinarySearchTree {

    public static BinaryTreeNode solution(BinaryTreeNode tree, int L, int R) {
        if(tree==null) {
            return null;
        }
        if(tree.val<L) {
            return solution(tree.right, L, R);
        }
        if(tree.val==L) {
            tree.left = null;
            tree.right = solution(tree.right, L, R);
        }
        if(tree.val>L&&tree.val<R) {
            tree.left = solution(tree.left, L, R);
            tree.right = solution(tree.right, L, R);
        }
        if(tree.val==R) {
            tree.left = solution(tree.left, L, R);
            tree.right = null;
        }
        if(tree.val>R) {
            return solution(tree.left, L, R);
        }
        return tree;
    }

}
