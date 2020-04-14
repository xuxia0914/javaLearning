package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.*;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes’ values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public static List<List<Integer>> solution(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) {
            return result;
        }
        Queue<Map<Integer, TreeNode>> q = new LinkedList<Map<Integer, TreeNode>>();
        Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
        map.put(1, root);
        q.offer(map);
        while(!q.isEmpty()) {
            Map<Integer, TreeNode> tmp = q.poll();
            for(Integer i : tmp.keySet()) {
                TreeNode node = tmp.get(i);
                if(result.size()<i) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(node.val);
                    result.add(list);
                }else {
                    if(i%2==0) {
                        result.get(i-1).add(0, node.val);
                    }else {
                        result.get(i-1).add(node.val);
                    }
                }
                if(node.left!=null) {
                    Map<Integer, TreeNode> left = new HashMap<Integer, TreeNode>();
                    left.put(i+1, node.left);
                    q.offer(left);
                }
                if(node.right!=null) {
                    Map<Integer, TreeNode> right = new HashMap<Integer, TreeNode>();
                    right.put(i+1, node.right);
                    q.offer(right);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> solution1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) {
            return result;
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int cnt = 0;
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> list = new LinkedList<>();
            while(n-->0) {
                TreeNode curr = q.poll();
                if(cnt%2==0) {
                    list.add(curr.val);
                }else {
                    list.add(0, curr.val);
                }
                if(curr.left!=null) {
                    q.offer(curr.left);
                }
                if(curr.right!=null) {
                    q.offer(curr.right);
                }
            }
            result.add(list);
            cnt++;
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
