package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 */
public class MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {
        if(root==null) {
            return 0;
        }
        if(root.left==null&&root.right==null) {
            return 1;
        }
        int level = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                TreeNode curr = queue.poll();
                if(curr.left!=null) {
                    if(curr.left.left==null&&curr.left.right==null) {
                        return level+1;
                    }
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    if(curr.right.left==null&&curr.right.right==null) {
                        return level+1;
                    }
                    queue.offer(curr.right);
                }
            }
            level++;
        }
        return level;
    }

}
