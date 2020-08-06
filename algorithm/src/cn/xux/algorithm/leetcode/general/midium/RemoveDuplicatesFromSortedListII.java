package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 *
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode curr = newHead;
        int pre = 0;
        boolean flag = false;   //标记pre有效性
        while(curr.next!=null) {
            if(flag&&curr.next.val==pre) {
                curr.next = curr.next.next;
            }else if(curr.next.next!=null&&curr.next.val==curr.next.next.val) {
                flag=true;
                pre = curr.next.val;
                curr.next = curr.next.next.next;
            }else {
                curr = curr.next;
            }
        }
        return newHead.next;
    }

}
