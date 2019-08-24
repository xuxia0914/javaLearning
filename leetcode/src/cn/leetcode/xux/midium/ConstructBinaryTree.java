package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

public class ConstructBinaryTree {

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 根据一棵树的前序遍历与中序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public BinaryTreeNode buildTreeFromPreorderAndInorderTraversal(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0) {
            return null;
        }
        return helper1(preorder, inorder, 0, 0, preorder.length);
    }

    public BinaryTreeNode helper1(int[] preorder, int[] inorder, int preStart, int inStart, int n) {
        BinaryTreeNode node = new BinaryTreeNode(preorder[preStart]);
        if(n==1) {
            return node;
        }
        int i;
        for(i=0;i<n;i++) {
            if(inorder[inStart+i]==preorder[preStart]) {
                break;
            }
        }
        if(i!=0) {
            node.left = helper1(preorder, inorder, preStart+1,inStart, i);
        }
        if(i!=n-1) {
            node.right = helper1(preorder, inorder, preStart+1+i,inStart+i+1, n-1-i);
        }
        return node;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意:
     * 你可以假设树中没有重复的元素。
     * 例如，给出
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    public BinaryTreeNode buildTreeFromInorderAndPostorderTraversal(int[] inorder, int[] postorder) {
        if(inorder==null||inorder.length==0) {
            return null;
        }
        return helper2(inorder, postorder, 0, 0, inorder.length);
    }

    public BinaryTreeNode helper2(int[] inorder, int[] postorder, int postStart, int inStart, int n) {
        BinaryTreeNode node = new BinaryTreeNode(postorder[postStart+n-1]);
        if(n==1) {
            return node;
        }
        int i;
        for(i=0;i<n;i++) {
            if(inorder[inStart+i]==postorder[postStart+n-1]) {
                break;
            }
        }
        if(i!=0) {
            node.left = helper2(inorder, postorder, postStart, inStart, i);
        }
        if(i!=n-1) {
            node.right = helper2(inorder, postorder, postStart+i,inStart+i+1, n-1-i);
        }
        return node;
    }

}
