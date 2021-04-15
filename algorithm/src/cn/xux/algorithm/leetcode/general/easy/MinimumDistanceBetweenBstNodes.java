package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 783. 二叉搜索树结点最小距离
 * 给定一个二叉搜索树的根结点 root，返回树中任意两节点的差的最小值。
 *
 * 示例：
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树结点对象(TreeNode object)，而不是数组。
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 *
 * 注意：
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 * 本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 */
public class MinimumDistanceBetweenBstNodes {

    public int minDiffInBST(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode curr = root;
        while(curr!=null) {
            deque.offerLast(curr);
            curr = curr.left;
        }
        curr = deque.pollLast();
        int pre = curr.val;
        curr = curr.right;
        while(curr!=null) {
            deque.offerLast(curr);
            curr = curr.left;
        }
        int result = Integer.MAX_VALUE;
        while(!deque.isEmpty()) {
            curr = deque.pollLast();
            result = Math.min(result, curr.val-pre);
            if(result==1) {
                return result;
            }
            pre = curr.val;
            curr = curr.right;
            while(curr!=null) {
                deque.offerLast(curr);
                curr = curr.left;
            }
        }
        return result;
    }

    int pre;
    int ans;

    public int minDiffInBST1(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null|| ans==1) {
            return;
        }
        dfs(root.left);
        if (pre != -1) {
            ans = Math.min(ans, root.val - pre);
        }
        pre = root.val;
        dfs(root.right);
    }

}
