package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.TreeNode;

import java.util.*;

/**
 * 872. 叶子相似的树
 * 请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * 举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。
 * 如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。
 * 如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。
 *
 * 提示：
 * 给定的两颗树可能会有 1 到 100 个结点。
 */
public class LeafSimilarTrees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if(root1==null&&root2==null) {
            return true;
        }
        if(root1==null||root2==null) {
            return false;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        TreeNode node1 = root1;
        while(node1!=null) {
            stack1.push(node1);
            node1 = node1.left;
        }
        List<Integer> list1 = new ArrayList();
        while(!stack1.isEmpty()) {
            TreeNode curr1 = stack1.pop();
            if(curr1.left==null&&curr1.right==null) {
                list1.add(curr1.val);
            }else {
                TreeNode right1 = curr1.right;
                while(right1!=null) {
                    stack1.push(right1);
                    right1 = right1.left;
                }
            }
        }
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode node2 = root2;
        while(node2!=null) {
            stack2.push(node2);
            node2 = node2.left;
        }
        int idx = 0;
        while(!stack2.isEmpty()) {
            TreeNode curr2 = stack2.pop();
            if(curr2.left==null&&curr2.right==null&&(idx>=list1.size()||list1.get(idx++)!=curr2.val)) {
                return false;
            }else {
                TreeNode right2 = curr2.right;
                while(right2!=null) {
                    stack2.push(right2);
                    right2 = right2.left;
                }
            }
        }
        return idx==list1.size();
    }

    public static void main(String[] args) {
        System.out.println(new LeafSimilarTrees().leafSimilar(new TreeNode(new Integer[]{3,5,1,6,2,9,8,null,null,7,4}),new TreeNode(new Integer[]{3,5,1,6,7,4,2,null,null,null,null,null,null,9,8})));
    }

}
