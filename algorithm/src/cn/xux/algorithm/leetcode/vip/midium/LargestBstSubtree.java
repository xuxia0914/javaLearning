package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 333. 最大 BST 子树
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，其中最大指的是子树节点数最多的。
 *
 * 注意:
 * 子树必须包含其所有后代。
 *
 * 示例:
 * 输入: [10,5,15,1,8,null,7]
 *    10
 *    / \
 *   5  15
 *  / \   \
 * 1   8   7
 * 输出: 3
 * 解释: 高亮部分为最大的 BST 子树。
 *      返回值 3 在这个样例中为子树大小。
 *
 * 进阶:
 * 你能想出用 O(n) 的时间复杂度解决这个问题吗？
 */
public class LargestBstSubtree {

    public int largestBSTSubtree(TreeNode root) {
        if(root==null) {
            return 0;
        }
        dfs(root);
        return ans;
    }

    int ans = 1;

    private Integer[] dfs(TreeNode node) {
        if(node==null) {
            return new Integer[]{0, null, null};
        }
        Integer[] leftAns = dfs(node.left);
        Integer[] rightAns = dfs(node.right);
        if(leftAns!=null&&rightAns!=null
                &&(leftAns[2]==null||leftAns[2]<node.val)
                &&(rightAns[1]==null||rightAns[1]>node.val)) {
            int sum = leftAns[0]+rightAns[0]+1;
            ans = Math.max(ans, sum);
            return new Integer[]{sum,
                    leftAns[1]==null?node.val:leftAns[1],
                    rightAns[2]==null?node.val:rightAns[2]};
        }else {
            return null;
        }
    }

}
