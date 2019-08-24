package cn.leetcode.xux.hard;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.*;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {

    /**递归*/
    public List<Integer> postorderTraversal(BinaryTreeNode root) {
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
    public List<Integer> postorderTraversal1(BinaryTreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        Set<BinaryTreeNode> set = new HashSet<>();
        BinaryTreeNode node = root;
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
        BinaryTreeNode root = new BinaryTreeNode(new Integer[]{1,null,2,3});
        System.out.println(btpt.postorderTraversal1(root));
    }

}
