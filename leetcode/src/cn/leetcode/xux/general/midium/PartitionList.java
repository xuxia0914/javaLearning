package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * Given a linked list and a value x,
 * partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 */
public class PartitionList {

    public static ListNode solution(ListNode node, int x) {
        ListNode node1 = new ListNode(0);
        node1.next = node;
        ListNode left = node1;
        ListNode right = node1;
        while(left.next!=null&&left.next.val<x) {
            left = left.next;
            right = left;
        }
        while(right.next!=null&&right.next.val>=x) {
            right = right.next;
        }
        while(right.next!=null) {
            ListNode tmp = left.next;
            left.next = right.next;
            right.next = right.next.next;
            left.next.next = tmp;
            left = left.next;
            while(right.next!=null&&right.next.val>=x) {
                right = right.next;
            }
        }
        return node1.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        ListNode result = solution(node1, 4);
        while(result!=null) {
            System.out.print(result.val+"->");
            result = result.next;
        }
    }

}
