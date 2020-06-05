package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 *
 * 说明：
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 *
 * 示例:
 * 输入:
 *     1
 *    / \
 *   2   3
 *  / \  /
 * 4  5 6
 * 输出: 6
 */
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if(root==null) {
            return 0;
        }
        int h_l = level(root.left);
        int h_r = level(root.right);
        if(h_l==h_r) {
            return (int)Math.pow(2, h_l)+countNodes(root.right);
        }else {
            return (int)Math.pow(2, h_r)+countNodes(root.left);
        }
    }

    public int level(TreeNode root) {
        TreeNode node = root;
        int h = 0;
        while(node!=null) {
            h++;
            node = node.left;
        }
        return h;
    }

}
