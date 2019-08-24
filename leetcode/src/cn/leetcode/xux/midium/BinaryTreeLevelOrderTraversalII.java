package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(BinaryTreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root==null) {
            return res;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<n;i++) {
                BinaryTreeNode node = queue.poll();
                list.add(node.val);
                if(node.left!=null) {
                    queue.offer(node.left);
                }
                if(node.right!=null) {
                    queue.offer(node.right);
                }
            }
            res.add(0, list);
        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversalII b = new BinaryTreeLevelOrderTraversalII();
        System.out.println(b.levelOrderBottom(new BinaryTreeNode(new Integer[]{3,9,20,null,null,15,7})));
    }

}
