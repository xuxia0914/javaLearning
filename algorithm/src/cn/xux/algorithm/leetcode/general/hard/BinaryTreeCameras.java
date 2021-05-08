package cn.xux.algorithm.leetcode.general.hard;

import cn.xux.algorithm.common.TreeNode;

/**
 * 968. 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 *
 * 示例 1：
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 *
 * 示例 2：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 *
 * 提示：
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 */
public class BinaryTreeCameras {

    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return ans[1];
    }

    private int[] dfs(TreeNode node) {
        // ans[0]: node必须装摄像头，覆盖所有节点需要的最小摄像头数目
        // ans[1]: 覆盖所有节点需要的最小摄像头数目
        // ans[2]: 覆盖所有子节点需要的最小摄像头数目
        int[] ans = new int[3];
        if(node==null) {
            ans[0] = Integer.MAX_VALUE/2;
            return ans;
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        ans[0] = left[2]+right[2]+1;
        ans[1] = Math.min(ans[0], Math.min(left[0]+right[1], right[0]+left[1]));
        ans[2] = Math.min(ans[0], left[1]+right[1]);
        return ans;
    }

}
