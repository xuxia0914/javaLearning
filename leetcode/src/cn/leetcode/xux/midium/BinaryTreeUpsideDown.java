package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * Given a binary tree where all the right nodes are either leaf nodes
 * with a sibling (a left node that shares the same parent node) or empty,
 * flip it upside down and turn it into a tree where the original right nodes
 * turned into left leaf nodes. Return the new root.
 *
 * For example:
 * Given a binary tree {1,2,3,4,5},
 * 　　  1
 * 　  / \
 * 　 2  3
 *   / \
 *  4  5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 * 　  4
 * 　 / \
 *   5  2
 *     / \
 *    3  1
 */
public class BinaryTreeUpsideDown {

    /**
     * 递归
     * @param root
     * @return
     */
    public BinaryTreeNode upsideDownBinaryTree(BinaryTreeNode root) {
        if(root==null) {
            return null;
        }
        BinaryTreeNode parent = root;
        BinaryTreeNode left = root.left;
        BinaryTreeNode right = root.right;
        if(left!=null) {
            BinaryTreeNode ret = upsideDownBinaryTree(left);
            left.left = right;
            left.right = parent;
            return ret;
        }
        return root;
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public BinaryTreeNode upsideDownBinaryTree1(BinaryTreeNode root) {
        if(root==null) {
            return null;
        }
        BinaryTreeNode curr = root;
        BinaryTreeNode parent = null;
        BinaryTreeNode parentRight = null;
        while(curr!=null) {
            BinaryTreeNode left = curr.left;
            curr.left = parentRight;
            parentRight = curr.right;
            curr.right = parent;
            parent = curr;
            curr = left;
        }
        return parent;
    }

}
