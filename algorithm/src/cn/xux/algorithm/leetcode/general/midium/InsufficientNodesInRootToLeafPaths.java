package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 1080. 根到叶路径上的不足节点
 * 给定一棵二叉树的根 root，请你考虑它所有 从根到叶的路径：从根到任何叶的路径。
 * （所谓一个叶子节点，就是一个没有子节点的节点）
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，
 * 则该节点被称之为「不足节点」，需要被删除。
 * 请你删除所有不足节点，并返回生成的二叉树的根。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 * 输出：[1,2,3,4,null,null,7,8,9,null,14]
 *
 * 示例 2：
 * 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 * 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
 *
 * 示例 3：
 * 输入：root = [5,-6,-6], limit = 0
 * 输出：[]
 *
 * 提示：
 * 给定的树有 1 到 5000 个节点
 * -10^5 <= node.val <= 10^5
 * -10^9 <= limit <= 10^9
 */
public class InsufficientNodesInRootToLeafPaths {

    //[1,2,-3,-5,null,4,null]
    //-1
    //[5,4,8,11,null,17,4,7,1,null,null,5,3]
    //22
    public static void main(String[] args) {
//        new InsufficientNodesInRootToLeafPaths().sufficientSubset(new TreeNode(new Integer[]{1,2,-3,-5,null,4,null}), -1);
        new InsufficientNodesInRootToLeafPaths().sufficientSubset(new TreeNode(new Integer[]{5,4,8,11,null,17,4,7,1,null,null,5,3}), 2);
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if(root==null) {
            return null;
        }
        int result = dfs(root, limit, 0);
        if(result<limit) {
            return null;
        }
        return root;
    }

    //return 当前节点向下路径和的最大值
    public int dfs(TreeNode node, int limit, int preSum) {
        if(node==null) {
            return 0;
        }
        int result = node.val;
        if(node.left==null&&node.right==null) {
            return result;
        }
        int downResult = Integer.MIN_VALUE;
        if(node.left!=null) {
            int leftResult = dfs(node.left, limit, preSum+node.val);
            if(preSum+node.val+leftResult<limit) {
                node.left = null;
            }
            downResult = Math.max(downResult, leftResult);
        }
        if(node.right!=null) {
            int rightResult = dfs(node.right, limit, preSum+node.val);
            if(preSum+node.val+rightResult<limit) {
                node.right = null;
            }
            downResult = Math.max(downResult, rightResult);
        }
        return node.val+downResult;
    }

}
