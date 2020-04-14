package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
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

    public static List<List<Integer>> solution(TreeNode tree) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(tree==null) {
            return result;
        }
        Queue<Map<Integer, TreeNode>> q = new LinkedList<Map<Integer, TreeNode>>();
        Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
        map.put(1, tree);
        q.offer(map);
        while(!q.isEmpty()) {
            Map<Integer, TreeNode> tmp = q.poll();
            for(Integer i : tmp.keySet()) {
                TreeNode node = tmp.get(i);
                if(result.size()<i) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(node.val);
                    result.add(0, list);
                }else {
                    result.get(0).add(node.val);
                }
                if(node.left!=null) {
                    Map<Integer, TreeNode> left = new HashMap<Integer, TreeNode>();
                    left.put(i+1, node.left);
                    q.offer(left);
                }
                if(node.left!=null) {
                    Map<Integer, TreeNode> right = new HashMap<Integer, TreeNode>();
                    right.put(i+1, node.right);
                    q.offer(right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(17);

        node3.left = node4;
        node3.right = node5;
        node1.left = node2;
        node1.right = node3;

        System.out.println(solution(node1));
    }

}
