package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

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
 *
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *  
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100 
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(queue.size()>0) {
            int size = queue.size();
            TreeNode curr = null;
            while(size-->0) {
                curr = queue.poll();
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
            ans.add(curr.val);
        }
        return ans;
    }

}
