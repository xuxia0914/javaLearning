package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */

public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode first = newHead;
        while(first!=null&&first.next!=null&&first.next.next!=null) {
            ListNode tmp = first.next;
            first.next = first.next.next;
            tmp.next = first.next.next;
            first.next.next = tmp;
            first = first.next.next;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode curr = node1;
        while (curr!=null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("");
//        ListNode node = solution1(node1);
        ListNode node = new SwapNodesInPairs().swapPairs(node1);
        while (node!=null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

}
