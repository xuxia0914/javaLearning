package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出:
 * 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步:
 * 0->1->2->NULL
 * 向右旋转 4 步:
 * 2->0->1->NULL
 */
public class RotateLinkList {

    public static ListNode solution1(ListNode node, int k) {
        if(node==null||node.next==null||k==0) {
            return node;
        }
        ListNode left = node;
        ListNode right = node;
        for(int i=0;i<k;i++) {
            ListNode header = left;
            right = right.next;
            while(right.next!=null) {
                right = right.next;
                left = left.next;
            }
            right.next = header;
            left.next = null;
            left = right;
        }
        return right;
    }

    /**
     * 更优解
     */
    public static ListNode solution2(ListNode head, int k) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode tail = head;
        int n = 1;
        while(tail.next!=null) {
            n++;
            tail = tail.next;
        }
        if(k%n==0) {
            return head;
        }
        k = n - k%n;
        ListNode newTail = head;
        while(--k >0) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
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
        ListNode rotated = solution2(node1, 9);
        while (rotated!=null) {
            System.out.print(rotated.val + "->");
            rotated = rotated.next;
        }
    }

}
