package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
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
