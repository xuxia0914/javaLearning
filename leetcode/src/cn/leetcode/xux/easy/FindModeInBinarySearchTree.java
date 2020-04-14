package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 找二分搜索数的众数
 * Given a binary search tree (BST) with duplicates, find all the mode(s)
 * (the most frequently occurred element) in the given BST.
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * For example:
 * Given BST [1,null,2,2],
 *    1
 *     \
 *      2
 *     /
 *    2
 * return [2].
 * Note: If a tree has more than one mode, you can return them in any order.
 * Follow up: Could you do that without using any extra space?
 * (Assume that the implicit stack space incurred due to recursion does not count).
 *
 * 思路：中序遍历是有序的
 *
 */
public class FindModeInBinarySearchTree {

    public int[] findMode(TreeNode root) {
        if(root==null) {
            return new int[]{};
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node!=null) {
            stack.push(node);
            node = node.left;
        }
        int max=0;
        int cnt=0;
        int pre = root.val;
        TreeNode curr;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            if(curr.val==pre) {
                cnt++;
            }else {
                if(cnt>max) {
                    max = cnt;
                    list.clear();
                    list.add(pre);
                }else if(cnt==max) {
                    list.add(pre);
                }
                cnt = 1;
                pre = curr.val;
            }
            curr = curr.right;
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        if(cnt>max) {
            list.clear();
            list.add(pre);
        }else if(cnt==max) {
            list.add(pre);
        }
        int[] res = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        FindModeInBinarySearchTree f = new FindModeInBinarySearchTree();
        f.findMode(new TreeNode(new Integer[]{2,1,2}));
    }

}
