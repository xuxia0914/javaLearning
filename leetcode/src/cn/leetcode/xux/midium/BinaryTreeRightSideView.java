package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(BinaryTreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) {
            return list;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        BinaryTreeNode curr;
        int size;
        while(!queue.isEmpty()) {
            size = queue.size();
            for(int i=0;i<size;i++) {
                curr = queue.poll();
                if(null!=curr.left) {
                    queue.offer(curr.left);
                }
                if(null!=curr.right) {
                    queue.offer(curr.right);
                }
                if(i==size-1) {
                    list.add(curr.val);
                }
            }
        }
        return list;
    }

}
