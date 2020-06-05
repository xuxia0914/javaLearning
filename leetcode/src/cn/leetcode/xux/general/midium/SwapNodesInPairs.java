package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */

public class SwapNodesInPairs {

    public static ListNode solution1(ListNode node) {
        if(node==null||node.next==null) {
            return node;
        }

        ListNode header = new ListNode(0);
        header.next = node;
        ListNode first = header;
        while(first.next.next!=null) {
            ListNode second = first.next;
            ListNode third = first.next.next;
            second.next = second.next.next;
            third.next = second;
            first.next = third;
            first = second;
        }
        return header.next;
    }

    public static ListNode solution2(ListNode node) {
        if(node==null||node.next==null) {
            return node;
        }

        ListNode second = node.next;
        node.next = solution2(node.next.next);
        second.next = node;

        return second;
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
        ListNode node = solution2(node1);
        while (node!=null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }

}
