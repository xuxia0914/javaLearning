package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * Input:
 *     2
 *    / \
 *   1   3
 * Output: true
 * Example 2:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * Output: false
 * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 *              is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(BinaryTreeNode root) {
        return helper(root, null, null);
    }

    public boolean helper(BinaryTreeNode root, Integer low, Integer high) {
        if(root==null) {
            return true;
        }
        if((low!=null&&root.val<=low)||(high!=null&&root.val>=high)) {
            return false;
        }
        boolean left = true;
        if(root.left!=null) {
            if(root.left.val<root.val) {
                left = helper(root.left, low, root.val);
            }else {
                return false;
            }
        }
        boolean right = true;
        if(root.right!=null) {
            if(root.right.val>root.val) {
                right = helper(root.right, root.val, high);
            }else {
                return false;
            }
        }
        return left&&right;
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        System.out.println(v.isValidBST(new BinaryTreeNode(new Integer[]{10,5,15,null,null,6,20})));
    }

}
