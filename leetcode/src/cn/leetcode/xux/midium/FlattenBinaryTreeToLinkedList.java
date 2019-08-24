package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Stack;

/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class FlattenBinaryTreeToLinkedList {
    /**
     * 递归
     * 执行用时 :2 ms, 在所有 Java 提交中击败了86.92%的用户
     * 内存消耗 :36.3 MB, 在所有 Java 提交中击败了80.92%的用户
     * @param root
     */
    public void flatten(BinaryTreeNode root) {
        if(root==null||(root.left==null&&root.right==null)) {
            return;
        }
        helper(root);
        reverse(root);
    }

    void helper(BinaryTreeNode root) {
        if(root==null||(root.left==null&&root.right==null)) {
            return;
        }
        helper(root.left);
        BinaryTreeNode node = root;
        while(node.left!=null) {
            node = node.left;
        }
        helper(root.right);
        node.left = root.right;
        root.right = null;
    }

    /**
     * 迭代
     * 执行用时 :3 ms, 在所有 Java 提交中击败了18.84%的用户
     * 内存消耗 :35.5 MB, 在所有 Java 提交中击败了82.25%的用户
     * @param root
     */
    public void flatten1(BinaryTreeNode root) {
        if(root==null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode node = root;
        while(node!=null) {
            stack.push(node);
            node = node.left;
        }
        BinaryTreeNode pre = root;
        while(!stack.isEmpty()) {
            BinaryTreeNode curr = stack.pop();
            if(curr.left==null) {
                pre = curr;
            }
            BinaryTreeNode right = curr.right;
            if(right!=null) {
                pre.left = right;
                curr.right = null;
            }
            while(right!=null) {
                stack.push(right);
                right = right.left;
            }
        }
        reverse(root);
    }

    void reverse(BinaryTreeNode root) {
        BinaryTreeNode node = root;
        while(node.left!=null) {
            node.right = node.left;
            node.left = null;
            node = node.right;
        }
    }

}
