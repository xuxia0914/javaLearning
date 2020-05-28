package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1372. 二叉树中的最长交错路径
 * 给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：
 * 选择二叉树中 任意 节点和一个方向（左或者右）。
 * 如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
 * 改变前进方向：左变右或者右变左。
 * 重复第二步和第三步，直到你在树中无法继续移动。
 * 交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。
 * 请你返回给定树中最长 交错路径 的长度。
 *
 * 示例 1：
 * 输入：root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
 * 输出：3
 * 解释：蓝色节点为树中最长交错路径（右 -> 左 -> 右）。
 *
 * 示例 2：
 * 输入：root = [1,1,1,null,1,null,null,1,1,null,1]
 * 输出：4
 * 解释：蓝色节点为树中最长交错路径（左 -> 右 -> 左 -> 右）。
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：0
 *
 * 提示：
 * 每棵树最多有 50000 个节点。
 * 每个节点的值在 [1, 100] 之间。
 */
public class LongestZigzagPathInABinaryTree {

    public int longestZigZag(TreeNode root) {
        dfs(root);
        return ans;
    }

    int ans = 0;

    public int[] dfs(TreeNode root) {
        if(root==null) {
            return new int[]{0, 0};
        }
        int[] leftAns = dfs(root.left);
        int[] rightAns = dfs(root.right);
        int[] currAns = new int[]{leftAns[1]+1, rightAns[0]+1};
        ans = Math.max(ans, Math.max(currAns[0], currAns[1]));
        return currAns;
    }

    public int longestZigZag1(TreeNode root) {
        Queue<HelperNode> queue = new LinkedList<>();
        queue.offer(new HelperNode(root, true, 0));
        int ans = 0;
        while(!queue.isEmpty()) {
            HelperNode curr = queue.poll();
            if(curr.turnLeft) {
                if(curr.tree.left!=null) {
                    queue.offer(new HelperNode(curr.tree.left, false, curr.len+1));
                }else {
                    ans = Math.max(ans, curr.len);
                }
                if(curr.tree.right!=null) {
                    queue.offer(new HelperNode(curr.tree.right, true, 1));
                }
            }else {
                if(curr.tree.right!=null) {
                    queue.offer(new HelperNode(curr.tree.right, true, curr.len+1));
                }else {
                    ans = Math.max(ans, curr.len);
                }
                if(curr.tree.left!=null) {
                    queue.offer(new HelperNode(curr.tree.left, false, 1));
                }
            }
        }
        return ans;
    }

}

class HelperNode {
    TreeNode tree;
    boolean turnLeft;
    int len;

    public HelperNode(TreeNode tree, boolean turnLeft, int len) {
        this.tree = tree;
        this.turnLeft = turnLeft;
        this.len = len;
    }

}
