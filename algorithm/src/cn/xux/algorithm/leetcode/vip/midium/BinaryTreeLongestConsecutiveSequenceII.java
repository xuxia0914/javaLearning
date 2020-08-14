package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 549. 二叉树中最长的连续序列（树上DP）
 * 给定一个二叉树，你需要找出二叉树中最长的连续序列路径的长度。
 * 请注意，该路径可以是递增的或者是递减。
 * 例如，[1,2,3,4] 和 [4,3,2,1] 都被认为是合法的，而路径 [1,2,4,3] 则不合法。
 * 另一方面，路径可以是 子-父-子 顺序，并不一定是 父-子 顺序。
 *
 * 示例 1:
 * 输入:
 *         1
 *        / \
 *       2   3
 * 输出: 2
 * 解释: 最长的连续路径是 [1, 2] 或者 [2, 1]。
 *
 * 示例 2:
 * 输入:
 *         2
 *        / \
 *       1   3
 * 输出: 3
 * 解释: 最长的连续路径是 [1, 2, 3] 或者 [3, 2, 1]。
 *
 * 注意: 树上所有节点的值都在 [-1e7, 1e7] 范围内。
 */
public class BinaryTreeLongestConsecutiveSequenceII {

    public int longestConsecutive2(TreeNode root) {
        // write your code here
        longestConsecutive(root);
        return ans;
    }

    int ans = 0;

    // res[0] 以当前节点为终点的升序的连续最长序列(从下往上)
    // res[1] 以当前节点为终点的降序的连续最长序列(从下往上)
    private int[] longestConsecutive(TreeNode node) {
        if(node==null) {
            return new int[2];
        }
        int[] left = longestConsecutive(node.left);
        int[] right = longestConsecutive(node.right);
        int[] res = new int[2];
        int currAns1 = 1;
        int currAns2 = 1;
        if(node.left==null||node.left.val+1==node.val) {
            res[0] = left[0]+1;
            currAns1 += left[0];
        }
        if(node.right==null||node.right.val+1==node.val) {
            res[0] = Math.max(res[0], right[0]+1);
            currAns2 += right[0];
        }
        if(node.left==null||node.left.val-1==node.val) {
            res[1] = left[1]+1;
            currAns2 += left[1];
        }
        if(node.right==null||node.right.val-1==node.val) {
            res[1] = Math.max(res[1], right[1]+1);
            currAns1 += right[1];
        }
        ans = Math.max(ans, Math.max(currAns1, currAns2));
        return res;
    }

}
