package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * Given a non-negative number represented as a singly linked list of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * Example:
 * Input:
 * 1->2->3
 * Output:
 * 1->2->4
 */
public class PlusOneLinkedList {

    public ListNode plusOne(ListNode head) {
        if(head==null) {
            return null;
        }
        head = ListNode.reverse1(head);
        ListNode curr = head;
        while(curr!=null&&curr.val==9) {
            curr.val = 0;
            curr = curr.next;
        }
        head = ListNode.reverse1(head);
        if(curr==null) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }else {
            curr.val++;
        }
        return head;
    }

    public static void main(String[] args) {
        PlusOneLinkedList ll = new PlusOneLinkedList();
        ListNode node1 = new ListNode(8);
        ListNode node2 = new ListNode(8);
        ListNode node3 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        ListNode res = ll.plusOne(node1);
        while(res!=null) {
            System.out.print(res.val + "->");
            res = res.next;
        }

    }

}
