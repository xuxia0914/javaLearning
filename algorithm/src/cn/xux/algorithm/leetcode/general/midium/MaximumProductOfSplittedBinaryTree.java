package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 1339. 分裂二叉树的最大乘积
 * 给你一棵二叉树，它的根为 root 。请你删除 1 条边，
 * 使二叉树分裂成两棵子树，且它们子树和的乘积尽可能大。
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取模后再返回。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6]
 * 输出：110
 * 解释：删除红色的边，得到 2 棵子树，和分别为 11 和 10 。它们的乘积是 110 （11*10）
 *
 * 示例 2：
 * 输入：root = [1,null,2,3,4,null,null,5,6]
 * 输出：90
 * 解释：移除红色的边，得到 2 棵子树，和分别是 15 和 6 。它们的乘积为 90 （15*6）
 *
 * 示例 3：
 * 输入：root = [2,3,9,10,7,8,6,5,4,11,1]
 * 输出：1025
 *
 * 示例 4：
 * 输入：root = [1,1]
 * 输出：1
 *
 * 提示：
 * 每棵树最多有 50000 个节点，且至少有 2 个节点。
 * 每个节点的值在 [1, 10000] 之间。
 */
public class MaximumProductOfSplittedBinaryTree {

    public static void main(String[] args) {
        System.out.println(new MaximumProductOfSplittedBinaryTree().maxProduct(
                new TreeNode(new Integer[]{1,2,3,4,5,6})
        ));
    }

    long sum;
    long leftSum;

    public int maxProduct(TreeNode root) {
        sum = dfs(root);
        leftSum = 0;
        nearest(root);
        return (int)(leftSum*(sum-leftSum)%1000000007);
    }

    public long dfs(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return dfs(root.left)+dfs(root.right)+root.val;
    }

    public long nearest(TreeNode root) {
        if(root==null) {
            return 0;
        }
        long ans = nearest(root.left)+nearest(root.right)+root.val;
        if(Math.abs(sum-2*ans)<Math.abs(sum-2*leftSum)) {
            leftSum = ans;
        }
        return ans;
    }

}
