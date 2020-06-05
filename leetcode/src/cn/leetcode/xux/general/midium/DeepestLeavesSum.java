package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1302. 层数最深叶子节点的和
 * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 *
 * 提示：
 * 树中节点数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 */
public class DeepestLeavesSum {

    //dfs
    public int deepestLeavesSum(TreeNode root) {
        int[] res = deepestLeavesSum(root, 1);
        return res[1];
    }

    //result[0] level, result[1] sum
    public int[] deepestLeavesSum(TreeNode root, int level) {
        if(root==null) {
            return new int[]{level, 0};
        }
        if(root.left==null&&root.right==null) {
            return new int[]{level, root.val};
        }
        int[] leftRes = deepestLeavesSum(root.left, level+1);
        int[] rightRes = deepestLeavesSum(root.right, level+1);
        if(leftRes[0]>rightRes[0]) {
            return leftRes;
        }else if(leftRes[0]<rightRes[0]) {
            return rightRes;
        }else {
            return new int[]{leftRes[0], leftRes[1]+rightRes[1]};
        }
    }

    //bfs
    public int deepestLeavesSum1(TreeNode root) {
        Queue<TreeNode>  queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            while(size-->0) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
            result = sum;
        }
        return result;
    }

}
