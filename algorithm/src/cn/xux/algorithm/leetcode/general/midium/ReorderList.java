package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 143. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *  L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1:
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 *
 * 示例 2:
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 *
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if(head==null||head.next==null||head.next.next==null) {
            return;
        }
        ListNode curr = head;
        int n = 0;
        while(curr!=null) {
            n++;
            curr = curr.next;
        }
        int mid = (n+1)/2;
        curr = head;
        while(mid-->1) {
            curr = curr.next;
        }
        ListNode node2 = curr.next;
        curr.next = null;
        node2 = reverse(node2);
        merge(head, node2);
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while(curr!=null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }

    private void merge(ListNode node1, ListNode node2) {
        ListNode curr = new ListNode(0);
        ListNode curr1 = node1;
        ListNode curr2 = node2;
        while(curr1!=null&&curr2!=null) {
            curr.next = curr1;
            curr1 = curr1.next;
            curr.next.next = curr2;
            curr2 = curr2.next;
            curr = curr.next.next;
        }
        if(curr1!=null) {
            curr.next = curr1;
        }
    }

}
