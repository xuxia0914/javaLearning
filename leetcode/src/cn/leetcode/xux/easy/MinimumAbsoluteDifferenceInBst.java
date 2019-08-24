package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给定一个所有节点为非负值的二叉搜索树，求树中任意两节点的差的绝对值的最小值。
 * 示例 :
 * 输入:
 *    1
 *     \
 *      3
 *     /
 *    2
 * 输出:
 * 1
 * 解释:
 * 最小绝对差为1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 注意: 树中至少有2个节点。
 */
public class MinimumAbsoluteDifferenceInBst {

    int res = Integer.MAX_VALUE;

    /**
     * 执行用时 :6 ms, 在所有 Java 提交中击败了37.87%的用户
     * 内存消耗 :40.5 MB, 在所有 Java 提交中击败了80.26%的用户
     * @param root
     * @return
     */
    public int getMinimumDifference(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            BinaryTreeNode curr = queue.poll();
            helper(curr);
            if(curr.left!=null&&!(curr.left.left==null&&curr.left.right==null)) {
                queue.add(curr.left);
            }
            if(curr.right!=null&&!(curr.right.left==null&&curr.right.right==null)) {
                queue.add(curr.right);
            }
        }
        return res;
    }

    public void helper(BinaryTreeNode node) {
        if(node.left!=null) {
            BinaryTreeNode left = node.left;
            while(left.right!=null) {
                left = left.right;
            }
            res = Math.min(node.val-left.val, res);
        }
        if(node.right!=null) {
            BinaryTreeNode right = node.right;
            while(right.left!=null) {
                right = right.left;
            }
            res = Math.min(right.val-node.val, res);
        }
    }

}
