package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * Given a singly linked list L: L 0→L 1→…→L n-1→L n,
 * reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…
 * You must do this in-place without altering the nodes' values.
 * For example,
 * Given{1,2,3,4}, reorder it to{1,4,2,3}.
 * 反转以后的链表是，从左往右是，node0->noden->node1->nodenn-1->.....
 */
public class ReorderLinkedList {

    public static ListNode solution(ListNode node) {
        if(node==null||node.next==null||node.next.next==null) {
            return node;
        }
        ListNode second = node.next;
        ListNode header = node;
        ListNode post = node;
        while(post.next.next!=null) {
            post=post.next;
        }
        header.next = post.next;
        post.next = null;
        header.next.next = solution(second);
        return header;
    }

    public void reorderList(ListNode head) {
        if(head==null||head.next==null||head.next.next==null) {
            return;
        }
        ListNode curr = head;
        ListNode second = head.next;
        ListNode tail = head;
        while(tail.next.next!=null) {
            tail = tail.next;
        }
        tail.next.next = second;
        curr.next = tail.next;
        tail.next = null;
        reorderList(second);
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next = node5;
        ListNode curr = node1;
        while (curr!=null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("");
        ListNode reList = solution(node1);
        while (reList!=null) {
            System.out.print(reList.val + "->");
            reList = reList.next;
        }
    }

}
