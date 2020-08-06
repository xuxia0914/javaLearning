package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 * 给定二叉树的根节点 root，找出存在于不同节点 A 和 B 之间的最大值 V，
 * 其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 *
 * 示例：
 * 输入：[8,3,10,1,6,null,14,null,null,4,7,13]
 * 输出：7
 * 解释：
 * 我们有大量的节点与其祖先的差值，其中一些如下：
 * |8 - 3| = 5
 * |3 - 7| = 4
 * |8 - 1| = 7
 * |10 - 13| = 3
 * 在所有可能的差值中，最大值 7 由 |8 - 1| = 7 得出。
 *
 * 提示：
 * 树中的节点数在 2 到 5000 之间。
 * 每个节点的值介于 0 到 100000 之间。
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

    int result = 0;

    public int maxAncestorDiff(TreeNode root) {
        dfs(root.left, root.val, root.val);
        dfs(root.right, root.val, root.val);
        return result;
    }

    public void dfs(TreeNode root, int max, int min) {
        if(root==null) {
            return;
        }
        result = Math.max(result, Math.max(Math.abs(root.val-max),Math.abs(root.val-min)));
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        dfs(root.left, max, min);
        dfs(root.right, max, min);
    }

}
