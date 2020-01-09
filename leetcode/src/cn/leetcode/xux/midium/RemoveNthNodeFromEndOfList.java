package cn.leetcode.xux.midium;


import cn.leetcode.xux.common.ListNode;

/**
 * 19. 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode left = newHead;
        ListNode right = newHead;
        while(n-->0) {
            right = right.next;
        }
        while(right.next!=null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return newHead.next;
    }

}
