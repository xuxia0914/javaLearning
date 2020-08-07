package cn.xux.algorithm.leetcode.vip.easy;

import cn.xux.algorithm.common.TreeNode;

/**
 * 270. 最接近的二叉搜索树值
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。
 *
 * 注意：
 * 给定的目标值 target 是一个浮点数
 * 题目保证在该二叉搜索树中只会存在一个最接近目标值的数
 *
 * 示例：
 * 输入: root = [4,2,5,1,3]，目标值 target = 3.714286
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 * 输出: 4
 */
public class ClosestBinarySearchTreeValue {

    public int closestValue(TreeNode root, double target) {
        int ans = root.val;
        TreeNode curr = root;
        while(curr!=null) {
            ans = Math.abs(target-ans)>Math.abs(target-curr.val)?ans:curr.val;
            curr = (double)curr.val>target?curr.left:curr.right;
        }
        return ans;
    }

}
