package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;
import cn.leetcode.xux.common.ListNode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example:
 * Given the sorted linked list: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedListToBinarySearchTree {

    public BinaryTreeNode sortedListToBST(ListNode head) {
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
            return new BinaryTreeNode(head.val);
        }
        BinaryTreeNode left, right, res;

        if(len==2) {
            left = new BinaryTreeNode(head.val);
            res = new BinaryTreeNode(head.next.val);
            res.left = left;
            return res;
        }
        ListNode pre = head;
        for(int i=0;i<len/2-1;i++) {
            pre = pre.next;
        }
        curr = pre.next;
        pre.next = null;
        res = new BinaryTreeNode(curr.val);
        left = sortedListToBST(head);
        right = sortedListToBST(curr.next);
        res.left = left;
        res.right = right;
        return res;
    }

    public static void main(String[] args) {
        ConvertSortedListToBinarySearchTree c = new ConvertSortedListToBinarySearchTree();
        ListNode head = new ListNode(new int[]{-10,-3,0,5,9,10});
        BinaryTreeNode res = c.sortedListToBST(head);
        System.out.println("---");
    }

}
