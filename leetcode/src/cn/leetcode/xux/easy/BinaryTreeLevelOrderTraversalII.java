package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

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

    public static List<List<Integer>> solution(BinaryTreeNode tree) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if(tree==null) {
            return result;
        }
        Queue<Map<Integer, BinaryTreeNode>> q = new LinkedList<Map<Integer, BinaryTreeNode>>();
        Map<Integer, BinaryTreeNode> map = new HashMap<Integer, BinaryTreeNode>();
        map.put(1, tree);
        q.offer(map);
        while(!q.isEmpty()) {
            Map<Integer, BinaryTreeNode> tmp = q.poll();
            for(Integer i : tmp.keySet()) {
                BinaryTreeNode node = tmp.get(i);
                if(result.size()<i) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(node.val);
                    result.add(0, list);
                }else {
                    result.get(0).add(node.val);
                }
                if(node.left!=null) {
                    Map<Integer, BinaryTreeNode> left = new HashMap<Integer, BinaryTreeNode>();
                    left.put(i+1, node.left);
                    q.offer(left);
                }
                if(node.left!=null) {
                    Map<Integer, BinaryTreeNode> right = new HashMap<Integer, BinaryTreeNode>();
                    right.put(i+1, node.right);
                    q.offer(right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeNode node1 = new BinaryTreeNode(3);
        BinaryTreeNode node2 = new BinaryTreeNode(9);
        BinaryTreeNode node3 = new BinaryTreeNode(20);
        BinaryTreeNode node4 = new BinaryTreeNode(15);
        BinaryTreeNode node5 = new BinaryTreeNode(17);

        node3.left = node4;
        node3.right = node5;
        node1.left = node2;
        node1.right = node3;

        System.out.println(solution(node1));
    }

}
