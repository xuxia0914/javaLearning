package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 1120：子树的最大平均值
 * 给你一棵二叉树的根节点 root，找出这棵树的 每一棵 子树的 平均值 中的 最大 值。
 * 子树是树中的任意节点和它的所有后代构成的集合。
 * 树的平均值是树中节点值的总和除以节点数。
 *
 * 示例：
 * 输入：[5,6,1]
 * 输出：6.00000
 * 解释：
 * 以 value = 5 的节点作为子树的根节点，得到的平均值为 (5 + 6 + 1) / 3 = 4。
 * 以 value = 6 的节点作为子树的根节点，得到的平均值为 6 / 1 = 6。
 * 以 value = 1 的节点作为子树的根节点，得到的平均值为 1 / 1 = 1。
 * 所以答案取最大值 6。
 *
 * 提示：
 * 树中的节点数介于 1 到 5000之间。
 * 每个节点的值介于 0 到 100000 之间。
 * 如果结果与标准答案的误差不超过 10^-5，那么该结果将被视为正确答案。
 */
public class MaximumAverageSubtree {

    public static void main(String[] args) {
        MaximumAverageSubtree ma = new MaximumAverageSubtree();
        System.out.println(ma.MaximumAverageSubtree(new TreeNode(new Integer[]{16,8,7,null,null,7})));
    }

    double ans;

    public double MaximumAverageSubtree(TreeNode root) {
        ans = Double.MIN_VALUE;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode node) {
        int[] curr = new int[2];
        if(node==null) {
            return curr;
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        curr[0] = left[0]+right[0]+1;
        curr[1] = left[1]+right[1]+node.val;
        ans = Math.max(ans, (double)curr[1]/curr[0]);
        return curr;
    }

}
