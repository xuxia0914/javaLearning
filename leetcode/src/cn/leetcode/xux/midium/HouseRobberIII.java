package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.TreeNode;

/**
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called the "root." Besides the root,
 * each house has one and only one parent house. After a tour,
 * the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * Example 1:
 * Input: [3,2,3,null,3,null,1]
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * Input: [3,4,5,1,3,null,1]
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {

    /**递归，太慢了*/
    public int rob(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int res = 0;
        res = Math.max(res, rob(root.left)+rob(root.right));
        int curr=root.val;
        if(root.left!=null) {
            curr += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right!=null) {
            curr += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(res, curr);
    }

    public int rob1(TreeNode root) {
        if (root == null) return 0;
        int[] ret = robHelper(root);
        return Math.max(ret[0], ret[1]);
    }

    private int[] robHelper(TreeNode root) {
        int[] ret = new int[2];
        if (root == null) return ret;
        int[] lRet = robHelper(root.left);
        int[] rRet = robHelper(root.right);

        ret[0] = Math.max(lRet[0], lRet[1]) + Math.max(rRet[0], rRet[1]);
        ret[1] = lRet[0] + rRet[0] + root.val;
        return ret;
    }

}
