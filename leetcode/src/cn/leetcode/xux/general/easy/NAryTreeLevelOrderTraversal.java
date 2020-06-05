package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.NAryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * 例如，给定一个 3叉树 :
 *           1
 *       ↙  ↓ ↘
 *      3    2  4
 *   ↙  ↘
 *  5     6
 *
 *
 * 返回其层序遍历:
 * [
 *      [1],
 *      [3,2,4],
 *      [5,6]
 * ]
 *
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 */
public class NAryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(NAryTreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null) {
            return res;
        }
        Queue<NAryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size-->0) {
                NAryTreeNode curr = queue.poll();
                list.add(curr.val);
                if(curr.children!=null||curr.children.size()>0) {
                    for(NAryTreeNode node : curr.children) {
                        queue.offer(node);
                    }
                }
            }
            res.add(list);
        }
        return res;
    }

}
