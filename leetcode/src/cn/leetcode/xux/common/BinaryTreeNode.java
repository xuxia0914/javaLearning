package cn.leetcode.xux.common;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树
 */
public class BinaryTreeNode {

    public int val;
    public BinaryTreeNode left;
    public BinaryTreeNode right;

    public BinaryTreeNode() {}

    public BinaryTreeNode(int _val) {
        val = _val;
    }

    public BinaryTreeNode(Integer[] vals) {
        if(vals==null||vals.length==0) {
            return;
        }
        BinaryTreeNode root = new BinaryTreeNode(vals[0]);
        BinaryTreeNode curr;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i=1;
        BinaryTreeNode left=null, right=null;
        while(i<vals.length) {
            curr = queue.poll();
            if(vals[i]==null) {
                left = null;
            }else {
                left = new BinaryTreeNode(vals[i]);
                queue.offer(left);
            }
            curr.left = left;
            i++;
            if(i<vals.length) {
                if(vals[i]==null) {
                    right = null;
                }else {
                    right = new BinaryTreeNode(vals[i]);
                    queue.offer(right);
                }
                curr.right = right;
            }
            i++;

        }
        this.val = root.val;
        this.left = root.left;
        this.right = root.right;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(new Integer[]{4,3,5,2,null,null,null,1,null});
    }

}
