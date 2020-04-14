package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf,
 * but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * Return 3. The paths that sum to 8 are:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class PathSumIII {

    int res = 0;

    public int pathSum(TreeNode root, int sum) {
        if(root==null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            helper(curr, sum, 0);
            if(curr.left!=null) {
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                queue.offer(curr.right);
            }
        }
        return res;
    }

    public void helper(TreeNode node, int sum, int preSum) {
        if(preSum+node.val==sum) {
            res++;
        }
        if(node.left!=null) {
            helper(node.left, sum, preSum+node.val);
        }
        if(node.right!=null) {
            helper(node.right, sum, preSum+node.val);
        }
    }

    public static void main(String[] args) {
        PathSumIII ps = new PathSumIII();
        System.out.println(ps.pathSum(new TreeNode(new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1}), 8));  //3
        System.out.println(ps.pathSum(new TreeNode(new Integer[]{1,null,2,null,3,null,4,null,5}), 3));    //5
    }

}
