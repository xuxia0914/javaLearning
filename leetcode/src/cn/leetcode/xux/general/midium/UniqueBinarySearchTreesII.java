package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树 II
 *
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class UniqueBinarySearchTreesII {

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(n<1) {
            return res;
        }
        return helper(1, n);
    }

    List<TreeNode> helper(int start, int end) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(start>end) {
            res.add(null);
            return res;
        }
        if(start==end) {
            res.add(new TreeNode(start));
            return res;
        }
        for(int i=start;i<=end;i++) {
            List<TreeNode> leftList = helper(start, i-1);
            List<TreeNode> rightList = helper(i+1, end);
            for(TreeNode left : leftList) {
                for(TreeNode right : rightList) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = left;
                    curr.right = right;
                    res.add(curr);
                }
            }
        }
        return res;
    }

}
