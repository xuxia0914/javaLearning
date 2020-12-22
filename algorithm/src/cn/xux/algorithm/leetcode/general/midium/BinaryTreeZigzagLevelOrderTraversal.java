package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.*;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
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

}
