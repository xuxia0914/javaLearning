package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1315. 祖父节点值为偶数的节点和
 * 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 * 如果不存在祖父节点值为偶数的节点，那么返回 0 。
 *
 * 示例：
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：18
 * 解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
 *
 * 提示：
 * 树中节点的数目在 1 到 10^4 之间。
 * 每个节点的值在 1 到 100 之间。
 */
public class SumOfNodesWithEvenValuedGrandparent {

    public int sumEvenGrandparent(TreeNode root) {
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left!=null) {
                if(curr.val%2==0) {
                    result += curr.left.left!=null?curr.left.left.val:0;
                    result += curr.left.right!=null?curr.left.right.val:0;
                }
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                if(curr.val%2==0) {
                    result += curr.right.left!=null?curr.right.left.val:0;
                    result += curr.right.right!=null?curr.right.right.val:0;
                }
                queue.offer(curr.right);
            }
        }
        return result;
    }

}
