package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * Note: A leaf is a node with no children.
 * Example:
 * Input:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * Output: ["1->2->5", "1->3"]
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        helper(res, root, "");
        return res;
    }

    public void helper(List<String> res, TreeNode node, String curr) {
        if(node.left==null&&node.right==null) {
            res.add(curr+node.val);
        }
        if(node.left!=null) {
            helper(res, node.left, curr+node.val+"->");
        }
        if(node.right!=null) {
            helper(res, node.right, curr+node.val+"->");
        }
    }

}
