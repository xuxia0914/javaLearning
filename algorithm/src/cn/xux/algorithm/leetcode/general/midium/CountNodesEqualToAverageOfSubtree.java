package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 2265. 统计值等于子树平均值的节点数
 * 给你一棵二叉树的根节点 root ，找出并返回满足要求的节点数，
 * 要求节点的值等于其 子树 中值的 平均值 。
 * <p>
 * 注意：
 * <p>
 * n 个元素的平均值可以由 n 个元素 求和 然后再除以 n ，
 * 并 向下舍入 到最近的整数。
 * root 的 子树 由 root 和它的所有后代组成。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [4,8,5,0,1,null,6]
 * 输出：5
 * 解释：
 * 对值为 4 的节点：子树的平均值 (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4 。
 * 对值为 5 的节点：子树的平均值 (5 + 6) / 2 = 11 / 2 = 5 。
 * 对值为 0 的节点：子树的平均值 0 / 1 = 0 。
 * 对值为 1 的节点：子树的平均值 1 / 1 = 1 。
 * 对值为 6 的节点：子树的平均值 6 / 1 = 6 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1]
 * 输出：1
 * 解释：对值为 1 的节点：子树的平均值 1 / 1 = 1。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 1000
 */
public class CountNodesEqualToAverageOfSubtree {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // 返回数组arr[0]: 树的节点个数  arr[1] 树的和
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] leftAns = dfs(root.left);
        int[] rightAns = dfs(root.right);
        int cnt = leftAns[0] + rightAns[0] + 1;
        int sum = leftAns[1] + rightAns[1] + root.val;
        if (sum / cnt == root.val) {
            ans++;
        }
        return new int[]{cnt, sum};
    }

}
