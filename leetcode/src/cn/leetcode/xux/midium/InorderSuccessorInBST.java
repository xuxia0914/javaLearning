package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Stack;

/**
 * 285. Inorder Successor in BST 二叉搜索树中的中序后继节点
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 * Example 1:
 * Input: root = [2,1,3], p = 1
 * Output: 2
 * Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
 *
 * Example 2:
 * Input: root = [5,3,6,2,4,null,null,1], p = 6
 * Output: null
 * Explanation: There is no in-order successor of the current node, so the answer is null.
 *
 * Note:
 * If the given node has no in-order successor in the tree, return null.
 * It's guaranteed that the values of the tree are unique.
 */
public class InorderSuccessorInBST {

    public BinaryTreeNode inorderSuccessor(BinaryTreeNode root, BinaryTreeNode p) {
        if(p.right!=null) {
            BinaryTreeNode node = p.right;
            while(node.left!=null) {
                node = node.left;
            }
            return node;
        }
        BinaryTreeNode pre = null;
        BinaryTreeNode node = root;
        while(node!=null) {
            if(node.val>p.val) {
                pre = node;
                node = node.left;
            }else if(node.val<p.val) {
                node = node.right;
            }else {
                break;
            }
        }
        return pre;
    }

    public static void main(String[] args) {
//        BinaryTreeNode root = new BinaryTreeNode(new Integer[]{5,3,6,2,4,null,null,1});
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left  = node1;

        InorderSuccessorInBST is = new InorderSuccessorInBST();
        System.out.println(is.inorderSuccessor(node5, node1).val);
        System.out.println(is.inorderSuccessor(node5, node2).val);
        System.out.println(is.inorderSuccessor(node5, node3).val);
        System.out.println(is.inorderSuccessor(node5, node4).val);
        System.out.println(is.inorderSuccessor(node5, node5).val);
        System.out.println(is.inorderSuccessor(node5, node6));
    }


}
