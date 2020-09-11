package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 1214：查找两棵二叉搜索树之和
 * 给出两棵二叉搜索树，请你从两棵树中各找出一个节点，
 * 使得这两个节点的值之和等于目标值 Target。
 * 如果可以找到返回 True，否则返回 False。
 *
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3], target = 5
 * 输出：true
 * 解释：2 加 3 和为 5 。
 *
 * 示例 2：
 * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
 * 输出：false
 *
 * 提示：
 * 每棵树上最多有 5000 个节点。
 * -10^9 <= target, node.val <= 10^9
 */
public class TwoSumBsts {

    public static void main(String[] args) {
        TwoSumBsts ts = new TwoSumBsts();
        System.out.println(ts.twoSumBSTs(
                new TreeNode(new Integer[]{2,1,4}),
                new TreeNode(new Integer[]{1,0,3}),
                4
        ));
        System.out.println(ts.twoSumBSTs(
                new TreeNode(new Integer[]{0,-10,10}),
                new TreeNode(new Integer[]{5,1,7,0,2}),
                10
        ));
    }

    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if(root1==null||root2==null) {
            return false;
        }
        int sum = root1.val+root2.val;
        if(sum<target) {
            return twoSumBSTs(root1.right, root2, target)
                    ||twoSumBSTs(root1, root2.right, target);
        }else if(sum>target) {
            return twoSumBSTs(root1.left, root2, target)
                    ||twoSumBSTs(root1, root2.left, target);
        }else {
            return true;
        }
    }

}
