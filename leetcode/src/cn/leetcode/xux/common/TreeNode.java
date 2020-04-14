package cn.leetcode.xux.common;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int _val) {
        val = _val;
    }

    public TreeNode(Integer[] vals) {
        if(vals==null||vals.length==0) {
            return;
        }
        TreeNode root = new TreeNode(vals[0]);
        TreeNode curr;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i=1;
        TreeNode left=null, right=null;
        while(i<vals.length) {
            curr = queue.poll();
            if(vals[i]==null) {
                left = null;
            }else {
                left = new TreeNode(vals[i]);
                queue.offer(left);
            }
            curr.left = left;
            i++;
            if(i<vals.length) {
                if(vals[i]==null) {
                    right = null;
                }else {
                    right = new TreeNode(vals[i]);
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
        TreeNode root = new TreeNode(new Integer[]{4,3,5,2,null,null,null,1,null});
    }

}
