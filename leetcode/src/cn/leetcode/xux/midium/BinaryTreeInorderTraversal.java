package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

    /**递归*/
    public List<Integer> inorderTraversal(BinaryTreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        List<Integer> leftList = inorderTraversal(root.left);
        List<Integer> rightList = inorderTraversal(root.right);
        res.addAll(leftList);
        res.add(root.val);
        res.addAll(rightList);
        return res;
    }

    /**迭代*/
    public List<Integer> inorderTraversal1(BinaryTreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode node = root;
        while(node!=null) {
            stack.push(node);
            node = node.left;
        }
        while(!stack.isEmpty()) {
            node = stack.pop();
            res.add(node.val);
            node = node.right;
            while(node!=null) {
                stack.push(node);
                node = node.left;
            }
        }
        return res;
    }

}
