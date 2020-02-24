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

    public void flatten(BinaryTreeNode root) {
        while(root!=null) {
            if(root.left==null) {
                root = root.right;
                continue;
            }
            BinaryTreeNode pre = root.left;
            while(pre.right!=null) {
                pre = pre.right;
            }
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
            root = root.right;
        }
    }

    public void flatten1(BinaryTreeNode root) {
        if(root==null) {
            return;
        }
        BinaryTreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        BinaryTreeNode curr = root;
        while(curr.right!=null) {
            curr = curr.right;
        }
        curr.right = tmp;
        flatten(root.right);
    }

}
