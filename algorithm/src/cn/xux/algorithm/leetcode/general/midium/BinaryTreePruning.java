package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.*;

/**
 * 814. 二叉树剪枝
 * 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 * 示例1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 * 示例2:
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * 示例3:
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * 说明:
 * 给定的二叉树最多有 100 个节点。
 * 每个节点的值只会为 0 或 1 。
 */
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        TreeNode newRoot = new TreeNode(1);
        newRoot.left = root;
        dfs(newRoot, root, true);
        return newRoot.left;
    }

    private void dfs(TreeNode curr, TreeNode parent, boolean isLeft) {
        if(curr.left!=null) {
            dfs(curr.left, curr, true);
        }
        if(curr.right!=null) {
            dfs(curr.right, curr, false);
        }
        if(curr.val==1) {
            return;
        }
        if(curr.left==null&&curr.right==null) {
            if(isLeft) {
                parent.left=null;
            }else {
                parent.right=null;
            }
        }
    }

    public TreeNode pruneTree1(TreeNode root) {
        if(root==null||(root.left==null&&root.right==null)) {
            return root;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root.left!=null) {
            queue.offer(root.left);
        }
        if(root.right!=null) {
            queue.offer(root.right);
        }
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            deque.offerLast(curr);
            if(curr.left!=null) {
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                queue.offer(curr.right);
            }
        }
        Map<TreeNode, Boolean> map = new HashMap<>();
        while(!deque.isEmpty()) {
            TreeNode curr = deque.pollLast();
            if(curr.val==1
                    ||(curr.left!=null&&!map.get(curr.left))
                    ||(curr.right!=null&&!map.get(curr.right))) {
                map.put(curr, false);
            }else {
                map.put(curr, true);
            }
        }
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left!=null) {
                if(map.get(curr.left)) {
                    curr.left = null;
                }else {
                    queue.offer(curr.left);
                }
            }
            if(curr.right!=null) {
                if(map.get(curr.right)) {
                    curr.right = null;
                }else {
                    queue.offer(curr.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        new BinaryTreePruning().pruneTree(new TreeNode(new Integer[]{1,null,0,0,1}));
    }

}
