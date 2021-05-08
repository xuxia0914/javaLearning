package cn.xux.algorithm.leetcode.general.hard;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 99. 恢复二叉搜索树
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 * 输入: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 * 输出: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 * 输出: [2,1,4,null,null,3]
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 * 进阶:
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        new RecoverBinarySearchTree().recoverTree(new TreeNode(new Integer[]{1,3,null,null,2}));
    }

    public void recoverTree(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode curr = root;
        while(curr!=null) {
            deque.offer(curr);
            curr = curr.left;
        }
        TreeNode left = null;
        TreeNode right = null;
        TreeNode pre = null;
        while(!deque.isEmpty()) {
            curr = deque.pollLast();
            if(pre!=null&&pre.val>curr.val) {
                if(left==null) {
                    left = pre;
                    right = curr;
                }else {
                    right = curr;
                    break;
                }
            }
            pre = curr;
            curr = curr.right;
            while(curr!=null) {
                deque.offer(curr);
                curr = curr.left;
            }
        }
        int tmp = left.val;
        left.val = right.val;
        right.val = tmp;
    }

}
