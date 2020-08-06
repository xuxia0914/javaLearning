package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 156. 上下翻转二叉树
 * 给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，
 * 将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。
 *
 * 例子:
 * 输入: [1,2,3,4,5]
 *
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * 输出: 返回二叉树的根 [4,5,2,#,#,3,1]
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1  
 *
 * 说明:
 * 对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。
 * 二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
 * 这里有一个例子:
 *    1
 *   / \
 *  2   3
 *     /
 *    4
 *     \
 *      5
 * 上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].
 */
public class BinaryTreeUpsideDown {

    /**
     * 递归
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode parent = root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left!=null) {
            TreeNode ret = upsideDownBinaryTree(left);
            left.left = right;
            left.right = parent;
            return ret;
        }
        return root;
    }

    /**
     * 迭代
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree1(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode curr = root;
        TreeNode parent = null;
        TreeNode parentRight = null;
        while(curr!=null) {
            TreeNode left = curr.left;
            curr.left = parentRight;
            parentRight = curr.right;
            curr.right = parent;
            parent = curr;
            curr = left;
        }
        return parent;
    }

}
