package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Stack;

/**
 * Given a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.
 * Example 1:
 * Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]
 *        5
 *       / \
 *     3    6
 *    / \    \
 *   2   4    8
 *  /        / \
 * 1        7   9
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 *             \
 *              7
 *               \
 *                8
 *                 \
 *                  9
 * Note:
 * The number of nodes in the given tree will be between 1 and 100.
 * Each node will have a unique integer value from 0 to 1000.
 */
public class IncreasingOrderSearchTree {

    public BinaryTreeNode increasingBST(BinaryTreeNode root) {
        if(root==null) {
            return null;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode curr = root;
        while(curr!=null) {
            stack.push(curr);
            curr = curr.left;
        }
        BinaryTreeNode res = new BinaryTreeNode();
        BinaryTreeNode tail = res;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            tail.left = null;
            tail.right = curr;
            tail = tail.right;
            curr = curr.right;
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        tail.left = null;
        tail.right = null;
        return res.right;
    }

    public static void main(String[] args) {
        IncreasingOrderSearchTree i = new IncreasingOrderSearchTree();
//        System.out.println(i.increasingBST(new BinaryTreeNode(new Integer[]{5,3,6,2,4,null,8,1,null,null,null,7,9})));
        System.out.println(i.increasingBST(new BinaryTreeNode(new Integer[]{1,2})));
    }

}
