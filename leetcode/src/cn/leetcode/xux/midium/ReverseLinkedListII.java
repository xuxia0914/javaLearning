package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * Reverse a linked list from position m to n. Do it in one-pass.
 * Note: 1 ≤ m ≤ n ≤ length of list.
 * Example:
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
public class ReverseLinkedListII {

    public static ListNode solution(ListNode node, int m, int n) {
        ListNode result = new ListNode(0);
        result.next = node;
        ListNode left = result;
        for(int i=0;i<m-1;i++) {
            left = left.next;
        }
        ListNode right = left.next;
        for(int i=0;i<n-m;i++) {
            ListNode tmp = left.next;
            left.next = right.next;
            right.next = right.next.next;
            left.next.next = tmp;
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        ListNode result = solution(node1, 1, 6);
        while(result!=null) {
            System.out.print(result.val+"->");
            result = result.next;
        }
    }

}
