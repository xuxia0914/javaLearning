package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5052. 最大层内元素和
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 * 请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
 *
 * 示例：
 * 输入：[1,7,0,7,-8,null,null]
 * 输出：2
 * 解释：
 * 第 1 层各元素之和为 1，
 * 第 2 层各元素之和为 7 + 0 = 7，
 * 第 3 层各元素之和为 7 + -8 = -1，
 * 所以我们返回第 2 层的层号，它的层内元素之和最大。
 *
 * 提示：
 * 树中的节点数介于 1 和 10^4 之间
 * -10^5 <= node.val <= 10^5
 */
public class MaximumLevelSumOfABinaryTree {

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            currLevel++;
            int currSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();
                currSum += currNode.val;
                if (currNode.left != null) {
                    queue.offer(currNode.left);
                }
                if (currNode.right != null) {
                    queue.offer(currNode.right);
                }
            }
            if (currSum > maxSum) {
                maxSum = currSum;
                maxLevel = currLevel;
            }
        }
        return maxLevel;
    }

}
