package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 369. 给单链表加一（递归）
 * 用一个 非空 单链表来表示一个非负整数，然后将这个整数加一。
 * 你可以假设这个整数除了 0 本身，没有任何前导的 0。
 * 这个整数的各个数位按照 高位在链表头部、低位在链表尾部 的顺序排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 */
public class PlusOneLinkedList {

    public ListNode plusOne(ListNode head) {
        if(head==null) {
            return null;
        }
        int carry = helper(head);
        if(carry==1) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    private int helper(ListNode node) {
        if(node.next==null) {
            int val = node.val+1;
            node.val = val%10;
            return val/10;
        }
        int val = node.val+helper(node.next);
        node.val = val%10;
        return val/10;
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
