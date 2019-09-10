package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Stack;

/**
 * 1022. 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 以 10^9 + 7 为模，返回这些数字之和。
 *
 * 示例：
 * 输入：[1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 * 提示：
 * 树中的结点数介于 1 和 1000 之间。
 * node.val 为 0 或 1 。
 */
public class SumOfRootToLeafBinaryNumbers {

    public int sumRootToLeaf(BinaryTreeNode root) {
        if(root==null) {
            return 0;
        }
        int res = 0;
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode curr;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            if(curr.left==null&&curr.right==null) {
                res += curr.val;
            }else {
                if(curr.left!=null) {
                    curr.left.val += curr.val*2;
                    stack.push(curr.left);
                }
                if(curr.right!=null) {
                    curr.right.val += curr.val*2;
                    stack.push(curr.right);
                }
            }
        }
        return res;
    }

}
