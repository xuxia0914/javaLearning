package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:给定如下二叉树，以及目标和 sum = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        helper(res, root, new ArrayList<Integer>(), sum);
        return res;
    }

    public void helper(List<List<Integer>> res, TreeNode node, List<Integer> list, int sum) {
        List<Integer> tmp = new ArrayList<>(list);
        if(node.left==null&&node.right==null) {
            tmp.add(node.val);
            for(int i : tmp) {
                sum -= i;
            }
            if(sum==0) {
                res.add(tmp);
            }
            return;
        }
        tmp.add(node.val);
        if(node.left!=null) {
            helper(res, node.left, tmp, sum);
        }
        if(node.right!=null) {
            helper(res, node.right, tmp, sum);
        }
    }

    public static void main(String[] args) {
        PathSumII ps = new PathSumII();
        System.out.println(ps.pathSum(new TreeNode(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1}), 22));
    }

}
