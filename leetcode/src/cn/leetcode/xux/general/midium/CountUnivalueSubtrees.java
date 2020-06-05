package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

/**
 * 250 Count Univalue Subtrees 计数相同值子树的个数
 * Given a binary tree, count the number of uni-value subtrees.
 * A Uni-value subtree means all nodes of the subtree have the same value.
 *
 * For example:
 * Given binary tree,
 *
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * return 4.
 */
public class CountUnivalueSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int res = 1;
        if(root.left!=null) {
            res += countUnivalSubtrees(root.left);;
            if(root.left.val==root.val) {
                res--;
            }
        }
        if(root.right!=null) {
            res += countUnivalSubtrees(root.right);
            if(root.right.val==root.val) {
                res--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        CountUnivalueSubtrees cus = new CountUnivalueSubtrees();
        System.out.println(cus.countUnivalSubtrees(new TreeNode(new Integer[]{5,1,5,5,5,5})));
    }

}
