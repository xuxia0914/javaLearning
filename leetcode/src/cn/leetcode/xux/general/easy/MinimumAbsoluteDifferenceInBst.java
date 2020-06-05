package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.Stack;

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

    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr!=null) {
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();
        int pre = curr.val;
        curr = curr.right;
        while(curr!=null) {
            stack.push(curr);
            curr = curr.left;
        }
        int result = Integer.MAX_VALUE;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            result = Math.min(result, curr.val-pre);
            pre = curr.val;
            curr = curr.right;
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        return result;
    }

}
