package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children.
 * Example:
 * Given the below binary tree and sum = 22,
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSumII {

    public List<List<Integer>> pathSum(BinaryTreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        helper(res, root, new ArrayList<Integer>(), sum);
        return res;
    }

    public void helper(List<List<Integer>> res, BinaryTreeNode node, List<Integer> list, int sum) {
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
        System.out.println(ps.pathSum(new BinaryTreeNode(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1}), 22));
    }

}
