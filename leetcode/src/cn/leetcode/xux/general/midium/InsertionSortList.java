package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * Sort a linked list using insertion sort.
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 * Algorithm of Insertion Sort:
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class InsertionSortList {

    public static ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        newHead.next = head;
        ListNode curr = newHead;
        ListNode tmp;
        while(curr.next!=null) {
            if(curr.next.val>=curr.val) {
                curr = curr.next;
                continue;
            }
            tmp = newHead;
            while(tmp.next!=null&&tmp.next.val<=curr.next.val) {
                tmp = tmp.next;
            }
            ListNode tmp1 = curr.next.next;
            curr.next.next = tmp.next;
            tmp.next = curr.next;
            curr.next = tmp1;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(3);
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
        ListNode reList = insertionSortList(node1);
        while (reList!=null) {
            System.out.print(reList.val + "->");
            reList = reList.next;
        }
    }

}
