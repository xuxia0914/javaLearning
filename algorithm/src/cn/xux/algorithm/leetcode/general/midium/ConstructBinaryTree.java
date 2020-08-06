package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

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
    public TreeNode buildTreeFromPreorderAndInorderTraversal(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0) {
            return null;
        }
        return helper1(preorder, inorder, 0, 0, preorder.length);
    }

    public TreeNode helper1(int[] preorder, int[] inorder, int preStart, int inStart, int n) {
        TreeNode node = new TreeNode(preorder[preStart]);
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
    public TreeNode buildTreeFromInorderAndPostorderTraversal(int[] inorder, int[] postorder) {
        if(inorder==null||inorder.length==0) {
            return null;
        }
        return helper2(inorder, postorder, 0, 0, inorder.length);
    }

    public TreeNode helper2(int[] inorder, int[] postorder, int postStart, int inStart, int n) {
        TreeNode node = new TreeNode(postorder[postStart+n-1]);
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

    /**
     * 889. 根据前序和后序遍历构造二叉树
     * 返回与给定的前序和后序遍历匹配的任何二叉树。
     *  pre 和 post 遍历中的值是不同的正整数。
     *
     * 示例：
     * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
     * 输出：[1,2,3,4,5,6,7]
     *
     * 提示：
     * 1 <= pre.length == post.length <= 30
     * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
     * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(pre==null||pre.length==0) {
            return null;
        }
        int len = pre.length;
        return helper3(pre, 0, len-1, post, 0, len-1);
    }

    public TreeNode helper3(int[] pre, int preStart, int preEnd,
            int[] post, int postStart, int postEnd) {
        if(preStart>preEnd) {
            return null;
        }
        TreeNode result = new TreeNode(pre[preStart]);
        if(preStart+1<=preEnd) {
            int i;
            for(i=postStart;i<=preEnd;i++) {
                if(post[i]==pre[preStart+1]) {
                    break;
                }
            }
            result.left = helper3(pre, preStart+1, i-postStart+preStart+1,
                    post, postStart, i);
            result.right = helper3(pre, i-postStart+preStart+2, preEnd,
                    post, i+1, postEnd-1);
        }
        return result;
    }

}
