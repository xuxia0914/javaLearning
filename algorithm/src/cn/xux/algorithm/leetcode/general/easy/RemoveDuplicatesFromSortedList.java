package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode curr = head;
        while(curr.next!=null) {
            if(curr.val==curr.next.val) {
                curr.next = curr.next.next;
            }else {
                curr = curr.next;
            }
        }
        return head;
    }

}
