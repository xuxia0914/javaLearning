package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1382. 将二叉搜索树变平衡
 * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。
 * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
 * 如果有多种构造方法，请你返回任意一种。
 *
 * 示例：
 * 输入：root = [1,null,2,null,3,null,4,null,null]
 * 输出：[2,1,3,null,null,null,4]
 * 解释：这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
 *
 * 提示：
 * 树节点的数目在 1 到 10^4 之间。
 * 树节点的值互不相同，且在 1 到 10^5 之间。
 */
public class BalanceABinarySearchTree {

    List<Integer> inOrder = new ArrayList<>();

    public TreeNode balanceBST(TreeNode root) {
        inOrder(root);
        return construct(inOrder, 0, inOrder.size()-1);
    }

    private void inOrder(TreeNode node) {
        if(node!=null) {
            inOrder(node.left);
            inOrder.add(node.val);
            inOrder(node.right);
        }
    }

    private TreeNode construct(List<Integer> inOrder, int left, int right) {
        if(left>right) {
            return null;
        }
        int mid = (left+right)/2;
        TreeNode ans = new TreeNode(inOrder.get(mid));
        ans.left = construct(inOrder, left, mid-1);
        ans.right = construct(inOrder, mid+1, right);
        return ans;
    }

}