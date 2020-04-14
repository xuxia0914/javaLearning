package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;
        if(root==null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left!=null) {
                if(curr.left.left==null&&curr.left.right==null) {
                    res += curr.left.val;
                }else {
                    queue.offer(curr.left);
                }
            }
            if(curr.right!=null&&(curr.right.left!=null||curr.right.right!=null)) {
                queue.offer(curr.right);
            }
        }
        return res;
    }

    public int sumOfLeftLeaves1(TreeNode root) {
        int res = 0;
        if(root==null) {
            return 0;
        }
        if(root.left!=null&&root.left.left==null&&root.left.right==null) {
            res += root.left.val;
        }
        res += sumOfLeftLeaves1(root.left) + sumOfLeftLeaves1(root.right);
        return res;
    }

}
