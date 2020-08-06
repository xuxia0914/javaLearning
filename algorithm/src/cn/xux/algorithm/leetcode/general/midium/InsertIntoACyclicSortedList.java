package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 在循环有序的链表中插入结点
 * Given a node from a cyclic linked list which is sorted in ascending order,
 * write a function to insert a value into the list such that it remains a cyclic sorted list.
 * The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value.
 * After the insertion, the cyclic list should remain sorted.
 * If the list is empty (i.e., given node is null),
 * you should create a new single cyclic list and return the reference to that single node.
 * Otherwise, you should return the original given node.
 */
public class InsertIntoACyclicSortedList {

    public static ListNode solution(ListNode head, int i) {
        if(head==null) {
            return new ListNode(i);
        }
        ListNode node = head;
        while(true) {
            if((node.val<i&&node.next.val>=i)
                    ||(node.val>node.next.val&&node.val<i)
                    ||(node.val>node.next.val&&node.next.val>=i)) {
                ListNode tmp = new ListNode(i);
                tmp.next = node.next;
                node.next = tmp;
                break;
            }else {
                node = node.next;
            }
        }
        return head;
    }


}
