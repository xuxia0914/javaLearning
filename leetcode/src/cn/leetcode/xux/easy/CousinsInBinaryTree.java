package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 993. 二叉树的堂兄弟节点
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 *
 * 示例 2：
 * 输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
 * 输出：true
 *
 * 示例 3：
 * 输入：root = [1,2,3,null,4], x = 2, y = 3
 * 输出：false
 *
 * 提示：
 * 二叉树的节点数介于 2 到 100 之间。
 * 每个节点的值都是唯一的、范围为 1 到 100 的整数。
 */
public class CousinsInBinaryTree {

    public boolean isCousins(TreeNode root, int x, int y) {
        if(root==null||(root.left==null||root.right==null)) {
            return false;
        }
        Queue<TreeNode[]> queue = new LinkedList<>();
        queue.offer(new TreeNode[]{root, null});
        TreeNode[] curr;
        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean flag1 = false;
            boolean flag2 = false;
            TreeNode parent1 = null;
            TreeNode parent2 = null;
            while(size-->0) {
                curr = queue.poll();
                if(curr[0].val==x) {
                    flag1 = true;
                    parent1 = curr[1];
                }
                if(curr[0].val==y) {
                    flag2 = true;
                    parent2 = curr[1];
                }
                if(flag1&&flag2&&parent1!=parent2) {
                    return true;
                }
                if(curr[0].left!=null) {
                    queue.offer(new TreeNode[]{curr[0].left, curr[0]});
                }
                if(curr[0].right!=null) {
                    queue.offer(new TreeNode[]{curr[0].right, curr[0]});
                }
            }
        }
        return false;
    }

}
