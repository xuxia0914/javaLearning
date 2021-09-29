package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class PathSumIII {

    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        if(root==null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            dfs(curr, targetSum, 0);
            if(curr.left!=null) {
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                queue.offer(curr.right);
            }
        }
        return res;
    }

    public void dfs(TreeNode node, int sum, int preSum) {
        if(preSum+node.val==sum) {
            res++;
        }
        if(node.left!=null) {
            dfs(node.left, sum, preSum+node.val);
        }
        if(node.right!=null) {
            dfs(node.right, sum, preSum+node.val);
        }
    }

    public static void main(String[] args) {
        PathSumIII ps = new PathSumIII();
        System.out.println(ps.pathSum(new TreeNode(new Integer[]{10,5,-3,3,2,null,11,3,-2,null,1}), 8));  //3
        System.out.println(ps.pathSum(new TreeNode(new Integer[]{1,null,2,null,3,null,4,null,5}), 3));    //5
    }

}
