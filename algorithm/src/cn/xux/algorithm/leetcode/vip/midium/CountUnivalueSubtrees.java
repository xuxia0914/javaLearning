package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 250.统计同值子树
 * 1.题目描述
 * 给定一个二叉树，统计该二叉树数值相同的子树个数。
 * 同值子树是指该子树的所有节点都拥有相同的数值。
 *
 * 示例：
 * 输入: root = [5,1,5,5,5,null,5]
 *               5
 *              / \
 *             1   5
 *            / \   \
 *           5   5   5
 * 输出：4
 */
public class CountUnivalueSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        if(root==null) {
            return 0;
        }
        dfs(root);
        return ans;
    }

    int ans = 0;

    private boolean dfs(TreeNode root) {
        boolean res = true;
        if(root.left!=null) {
            boolean leftRes = dfs(root.left);
            if(!leftRes&&root.val!=root.left.val) {
                res = false;
            }
        }
        if(root.right!=null) {
            boolean rightRes = dfs(root.right);
            if(!rightRes&&root.val!=root.right.val) {
                res = false;
            }
        }
        ans += res?1:0;
        return res;
    }

    public static void main(String[] args) {
        CountUnivalueSubtrees cus = new CountUnivalueSubtrees();
        System.out.println(cus.countUnivalSubtrees(new TreeNode(new Integer[]{5,1,5,5,5,5})));
    }

}
