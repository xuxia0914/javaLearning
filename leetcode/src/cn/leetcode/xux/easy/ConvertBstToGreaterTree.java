package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Stack;

/**
 * 538. 把二叉搜索树转换为累加树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 * 输入: 二叉搜索树:
 *               5
 *             /   \
 *            2     13
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 */
public class ConvertBstToGreaterTree {

    public BinaryTreeNode convertBST(BinaryTreeNode root) {
        if(root==null) {
            return root;
        }
        int pre = 0;
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode node = root;
        while(node!=null) {
            stack.push(node);
            node = node.right;
        }
        while(!stack.isEmpty()) {
            BinaryTreeNode curr = stack.pop();
            curr.val += pre;
            pre = curr.val;
            BinaryTreeNode left = curr.left;
            while(left!=null) {
                stack.push(left);
                left = left.right;
            }
        }
        return root;
    }

}
