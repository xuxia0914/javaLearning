package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNode;

/**
 *奇偶链表
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class OddEvenLinkedList {

    public static void solution(ListNode node) {
        if(node==null||node.next==null||node.next.next==null) {
            return;
        }
        ListNode odd = node;
        ListNode next = node.next, even = node.next;
        while(even!=null&&even.next!=null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = next;
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
        ListNode node = node1;
        while(node1!=null) {
            System.out.print(node1.val+"->");
            node1 = node1.next;
        }
        System.out.println();
        solution(node);
        while(node!=null) {
            System.out.print(node.val+"->");
            node = node.next;
        }
    }

}
