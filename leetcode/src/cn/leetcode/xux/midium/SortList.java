package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode left=head, right=head, mid=head, res = new ListNode(0), curr=res;
        while(right.next!=null&&right.next.next!=null) {
            right=right.next.next;
            mid = mid.next;
        }
        right = mid.next;
        mid.next = null;
        left = sortList(left);
        right = sortList(right);
        while(left!=null||right!=null) {
            while(left==null) {
                curr.next = right;
                return res.next;
            }
            while(right==null) {
                curr.next = left;
                return res.next;
            }
            if(left.val<right.val) {
                curr.next = left;
                curr = curr.next;
                left = left.next;
            }else{
                curr.next = right;
                curr = curr.next;
                right = right.next;
            }
        }
        return res.next;
    }

}
