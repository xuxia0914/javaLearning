package cn.leetcode.xux.hard;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: 42
 */
public class BinaryTreeMaximumPathSum {

    private static int max = Integer.MIN_VALUE;

    public int maxPathSum(BinaryTreeNode root) {
        maxSum(root);
        return max;
    }

    public int maxSum(BinaryTreeNode root) { //从root节点出发的路径的最大路径
        if (root == null) return 0;
        int leftVal = maxSum(root.left);    //递归求左支路的最大路径和
        int rightVal = maxSum(root.right);  //递归求右支路的最大路径和
        //如果当前局部解（root或left+root或root+right或left+root+right）是最有解，更新最终结果
        int curMax = root.val;
        if (leftVal > 0) {
            curMax += leftVal;
        }
        if (rightVal > 0) {
            curMax += rightVal;
        }
        if (curMax > max) {
            max = curMax;
        }
        //返回从叶子结点到root的最大路径和（root或left+root或root+right）
        return Math.max(root.val, Math.max(root.val + leftVal, root.val + rightVal));
    }

    public static void main(String[] args) {
        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(new BinaryTreeNode(new Integer[]{-10,9,20,null,null,15,7})));
    }

}
