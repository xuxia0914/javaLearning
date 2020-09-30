package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 输入:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        ans = new ArrayList<>();
        if(root==null) {
            return ans;
        }
        dfs(root, "");
        return ans;
    }

    List<String> ans;

    public void dfs(TreeNode node, String curr) {
        if(node.left==null&&node.right==null) {
            ans.add(curr+node.val);
        }
        if(node.left!=null) {
            dfs(node.left, curr+node.val+"->");
        }
        if(node.right!=null) {
            dfs(node.right, curr+node.val+"->");
        }
    }

}
