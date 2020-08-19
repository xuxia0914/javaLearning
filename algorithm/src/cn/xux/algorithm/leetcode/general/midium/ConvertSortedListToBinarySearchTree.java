package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;
import cn.xux.algorithm.common.ListNode;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while(curr!=null) {
            len++;
            curr = curr.next;
        }
        return sortedListToBST(head, len);
    }

    private TreeNode sortedListToBST(ListNode node, int len) {
        if(len==0) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        ListNode pre = newHead;
        pre.next = node;
        int step = (len-1)/2;
        while(step-->0) {
            pre = pre.next;
        }
        TreeNode ans = new TreeNode(pre.next.val);
        TreeNode right = sortedListToBST(pre.next.next, len/2);
        pre.next = null;
        TreeNode left = sortedListToBST(newHead.next, (len-1)/2);
        ans.left = left;
        ans.right = right;
        return ans;
    }

    public TreeNode sortedListToBST1(ListNode head) {
        if(head==null) {
            return null;
        }
        int len = 0;
        ListNode curr = head;
        while(curr!=null) {
            len++;
            curr = curr.next;
        }
        if(len==1) {
            return new TreeNode(head.val);
        }
        TreeNode left, right, res;

        if(len==2) {
            left = new TreeNode(head.val);
            res = new TreeNode(head.next.val);
            res.left = left;
            return res;
        }
        ListNode pre = head;
        for(int i=0;i<len/2-1;i++) {
            pre = pre.next;
        }
        curr = pre.next;
        pre.next = null;
        res = new TreeNode(curr.val);
        left = sortedListToBST(head);
        right = sortedListToBST(curr.next);
        res.left = left;
        res.right = right;
        return res;
    }

    public static void main(String[] args) {
        ConvertSortedListToBinarySearchTree c = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(new int[]{-10,-3,0,5,9,10});
        TreeNode res = c.sortedListToBST(head);
        System.out.println("---");
    }

}
