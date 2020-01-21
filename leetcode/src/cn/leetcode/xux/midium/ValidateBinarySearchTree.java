package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(BinaryTreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(BinaryTreeNode root, Integer low, Integer high) {
        if(root==null) {
            return true;
        }
        if(low!=null&&low>=root.val) {
            return false;
        }
        if(high!=null&&high<=root.val) {
            return false;
        }
        if(!isValidBST(root.left, low, root.val)) {
            return false;
        }
        if(isValidBST(root.right, root.val, high)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        System.out.println(v.isValidBST(new BinaryTreeNode(new Integer[]{10,5,15,null,null,6,20})));
    }

}
