package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

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

    public static List<List<Integer>> solution(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) {
            return result;
        }
        Queue<Map<Integer, BinaryTreeNode>> q = new LinkedList<Map<Integer, BinaryTreeNode>>();
        Map<Integer, BinaryTreeNode> map = new HashMap<Integer, BinaryTreeNode>();
        map.put(1, root);
        q.offer(map);
        while(!q.isEmpty()) {
            Map<Integer, BinaryTreeNode> tmp = q.poll();
            for(Integer i : tmp.keySet()) {
                BinaryTreeNode node = tmp.get(i);
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
                    Map<Integer, BinaryTreeNode> left = new HashMap<Integer, BinaryTreeNode>();
                    left.put(i+1, node.left);
                    q.offer(left);
                }
                if(node.right!=null) {
                    Map<Integer, BinaryTreeNode> right = new HashMap<Integer, BinaryTreeNode>();
                    right.put(i+1, node.right);
                    q.offer(right);
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> solution1(BinaryTreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null) {
            return result;
        }
        Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
        q.offer(root);
        int cnt = 0;
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> list = new LinkedList<>();
            while(n-->0) {
                BinaryTreeNode curr = q.poll();
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
