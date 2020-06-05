package cn.leetcode.xux.general.easy;

import cn.leetcode.xux.common.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 * Example:
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements {

    public ListNode removeElements(ListNode head, int val) {
        if(head==null) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode curr = newHead;
        while(curr.next!=null) {
            if(curr.next.val==val) {
                curr.next = curr.next.next;
            }else {
                curr = curr.next;
            }
        }
        return newHead.next;
    }

}
