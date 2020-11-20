package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 147. 对链表进行插入排序
 * 对链表进行插入排序。
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
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
