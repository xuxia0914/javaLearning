package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {

    public static boolean solution(ListNode node) {
        if(node==null) {
            return true;
        }
        int length = 0;
        ListNode node1 = node;
        while(node1!=null) {
            length++;
            node1=node1.next;
        }
        int mid = (length-1)/2+1;
        ListNode node2 = node;
        while(mid>0) {
            node2 = node2.next;
            mid--;
        }
        ListNode node3 = ListNode.reverse2(node2);
        ListNode node4 = node;
        while(node3!=null) {
            if(node3.val!=node4.val) {
                return false;
            }
            node3 = node3.next;
            node4 = node4.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(2);
        ListNode node7 = new ListNode(1);
        node6.next = node7;
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        System.out.println(solution(node1));
    }

}
