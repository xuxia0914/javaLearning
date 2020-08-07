package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * 285. 二叉搜索树中的顺序后继
 * 给你一个二叉搜索树和其中的某一个结点，请你找出该结点在树中顺序后继的节点。
 * 结点 p 的后继是值比 p.val 大的结点中键值最小的结点。
 *
 * 示例 1:
 * 输入: root = [2,1,3], p = 1
 * 输出: 2
 * 解析: 这里 1 的顺序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * 输出: null
 * 解析: 因为给出的结点没有顺序后继，所以答案就返回 null 了。
 *
 * 注意:
 * 假如给出的结点在该树中没有顺序后继的话，请返回 null
 * 我们保证树中每个结点的值是唯一的
 */
public class InorderSuccessorInBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p.right!=null) {
            TreeNode node = p.right;
            while(node.left!=null) {
                node = node.left;
            }
            return node;
        }
        TreeNode pre = null;
        TreeNode node = root;
        while(node!=null) {
            if(node.val>p.val) {
                pre = node;
                node = node.left;
            }else if(node.val<p.val) {
                node = node.right;
            }else {
                break;
            }
        }
        if(node.right!=null) {
            node = node.right;
            while(node.left!=null) {
                node = node.left;
            }
            return node;
        }
        return pre;
    }

    public static void main(String[] args) {
//        BinaryTreeNode root = new BinaryTreeNode(new Integer[]{5,3,6,2,4,null,null,1});
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left  = node1;

        InorderSuccessorInBST is = new InorderSuccessorInBST();
        System.out.println(is.inorderSuccessor(node5, node1).val);
        System.out.println(is.inorderSuccessor(node5, node2).val);
        System.out.println(is.inorderSuccessor(node5, node3).val);
        System.out.println(is.inorderSuccessor(node5, node4).val);
        System.out.println(is.inorderSuccessor(node5, node5).val);
        System.out.println(is.inorderSuccessor(node5, node6));
    }


}
