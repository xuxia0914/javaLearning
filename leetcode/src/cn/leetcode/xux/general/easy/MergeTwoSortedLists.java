package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.ListNode;

/**
 * 21. 合并两个有序链表
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoSortedLists {

    //不破坏原来的链表结构
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while(node1!=null||node2!=null) {
            if(node1==null) {
                ListNode tmp = new ListNode(node2.val);
                curr.next = tmp;
                curr = curr.next;
                node2 = node2.next;
            }else if(node2==null) {
                ListNode tmp = new ListNode(node1.val);
                curr.next = tmp;
                curr = curr.next;
                node1 = node1.next;
            }else {
                if(node1.val>node2.val) {
                    ListNode tmp = new ListNode(node2.val);
                    curr.next = tmp;
                    curr = curr.next;
                    node2 = node2.next;
                }else {
                    ListNode tmp = new ListNode(node1.val);
                    curr.next = tmp;
                    curr = curr.next;
                    node1 = node1.next;
                }
            }
        }
        return head.next;
    }

    //破坏原来的链表结构
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while(l1!=null&&l2!=null) {
            if(l1.val<=l2.val) {
                curr.next = l1;
                l1 = l1.next;
                curr = curr.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
                curr = curr.next;
            }
        }
        curr.next = l1==null?l2 : l1;
        return head.next;
    }

}
