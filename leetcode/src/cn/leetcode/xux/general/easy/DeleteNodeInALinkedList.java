package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.ListNode;

/**
 * 删除链表的节点
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
 * the linked list should become 1 -> 2 -> 4 after calling your function.
 */
public class DeleteNodeInALinkedList {

    public static void solution(ListNode node) {
        if(node.next==null) {
            node = null;
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }

}
