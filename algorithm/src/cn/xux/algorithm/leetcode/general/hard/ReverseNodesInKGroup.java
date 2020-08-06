package cn.xux.algorithm.leetcode.general.hard;

import cn.xux.algorithm.common.ListNode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * Example:
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 */
public class ReverseNodesInKGroup {

    public static ListNode solution(ListNode node, int k) {
        if(node==null) {
            return null;
        }
        int n = k;
        ListNode pre = node;
        while(k>1&&pre.next!=null) {
            pre = pre.next;
            k--;
        }
        if(k>1) {
            return node;
        }
        ListNode behind = pre.next;
        pre.next = null;

        ListNode node1 = ListNode.reverse1(node);
        ListNode node2 = node1;
        while(node2.next!=null) {
            node2 = node2.next;
        }
        node2.next = solution(behind, n);
        return node1;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        ListNode result = solution(node1, 3);
        while(result!=null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}
