package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.Stack;

/**
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 *
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 */
public class KthSmallestElementInABst {

    //中序遍历
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node!=null) {
            stack.push(node);
            node = node.left;
        }
        while(k>0) {
            TreeNode curr = stack.pop();
            k--;
            if(k==0) {
                return curr.val;
            }
            TreeNode right = curr.right;
            while(right!=null) {
                stack.push(right);
                right = right.left;
            }
        }
        return -1;
    }

}
