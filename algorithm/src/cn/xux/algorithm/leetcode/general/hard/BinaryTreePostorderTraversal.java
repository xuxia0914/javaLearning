package cn.xux.algorithm.leetcode.general.hard;

import cn.xux.algorithm.common.TreeNode;

import java.util.*;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class BinaryTreePostorderTraversal {

    /**递归*/
    public List<Integer> postorderTraversal(cn.xux.algorithm.common.TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        List<Integer> leftList = postorderTraversal(root.left);
        List<Integer> rightList = postorderTraversal(root.right);
        res.addAll(leftList);
        res.addAll(rightList);
        res.add(root.val);
        return res;
    }

    /**迭代*/
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>();
        TreeNode node = root;
        while(node!=null) {
            stack.push(node);
            node = node.left;
        }
        while(!stack.isEmpty()) {
            node = stack.peek();
            if(set.add(node)&&node.right!=null) {
                node = node.right;
                while(node!=null) {
                    stack.push(node);
                    node = node.left;
                }
            }else {
                node = stack.pop();
                res.add(node.val);
            }

        }
        return res;
    }

    public static void main(String[] args) {
        BinaryTreePostorderTraversal btpt = new BinaryTreePostorderTraversal();
        cn.xux.algorithm.common.TreeNode root = new TreeNode(new Integer[]{1,null,2,3});
        System.out.println(btpt.postorderTraversal1(root));
    }

}
