package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

import java.util.Stack;

/**
 *
 * 897. 递增顺序搜索树
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 *        5
 *       / \
 *     3    6
 *    / \    \
 *   2   4    8
 *  /        / \
 * 1        7   9
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
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
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 *
 * 提示：
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 */
public class IncreasingOrderSearchTree {

    public TreeNode increasingBST(TreeNode root) {
        if(root==null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr!=null) {
            stack.push(curr);
            curr = curr.left;
        }
        TreeNode res = new TreeNode();
        TreeNode tail = res;
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
        System.out.println(i.increasingBST(new TreeNode(new Integer[]{1,2})));
    }

}
