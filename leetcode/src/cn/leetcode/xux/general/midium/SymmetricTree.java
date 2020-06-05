package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明:
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 */
public class SymmetricTree {

    /**
     * 递归
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.78%的用户
     * 内存消耗 :35.7 MB, 在所有 Java 提交中击败了80.01%的用户
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root==null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode left, TreeNode right) {
        if(left==null&&right==null) {
            return true;
        }
        if(left==null||right==null) {
            return false;
        }
        if(left.val!=right.val) {
            return false;
        }
        return helper(left.left, right.right)&&helper(left.right, right.left);
    }

    /**
     * 迭代
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.78%的用户
     * 内存消耗 :35.8 MB, 在所有 Java 提交中击败了79.70%的用户
     * @param root
     * @return
     */
    public boolean isSymmetric1(TreeNode root) {
        if(root==null||(root.left==null&&root.right==null)) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left==null||right==null||left.val!=right.val) {
            return false;
        }
        Queue<TreeNode> leftQueue = new LinkedList<>();
        leftQueue.offer(left);
        Queue<TreeNode> rightQueue = new LinkedList<>();
        rightQueue.offer(right);
        while(!leftQueue.isEmpty()&&!rightQueue.isEmpty()) {
            TreeNode currLeft = leftQueue.poll();
            TreeNode currRight = rightQueue.poll();
            if(currLeft.left!=null&&currRight.right!=null) {
                if(currLeft.left.val!=currRight.right.val) {
                    return false;
                }
                leftQueue.offer(currLeft.left);
                rightQueue.offer(currRight.right);
            }
            if((currLeft.left==null&&currRight.right!=null)
                    ||(currLeft.left!=null&&currRight.right==null)){
                return false;
            }
            if(currLeft.right!=null&&currRight.left!=null) {
                if(currLeft.right.val!=currRight.left.val) {
                    return false;
                }
                leftQueue.offer(currLeft.right);
                rightQueue.offer(currRight.left);
            }
            if((currLeft.right==null&&currRight.left!=null)
                    ||(currLeft.right!=null&&currRight.left==null)){
                return false;
            }
        }
        return true;
    }

}
