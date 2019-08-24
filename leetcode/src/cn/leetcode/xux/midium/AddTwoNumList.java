package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * 给定两个链表，算出它们的和，如1->2->3 + 2->9->3 = 3->1->7
 */
public class AddTwoNumList {

    public static ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode result = new ListNode(0);
        ListNode p = a, q=b, curr = result;
        int carry=0;
        while(p!=null||q!=null) {
            int x = (p!=null)?p.val:0;
            int y = (q!=null)?q.val:0;
            int sum = carry + x + y;
            if(sum>=10) {
                curr.next = new ListNode(sum%10);
                carry = 1;
            }else {
                curr.next = new ListNode(sum);
                carry = 0;
            }

            if(p!=null) {
                p = p.next;
            }
            if(q!=null) {
                q = q.next;
            }
            curr = curr.next;
        }
        if(carry>0) {
            curr.next = new ListNode(carry);;
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(2);
        a.next.next  = new ListNode(3);

        ListNode b = new ListNode(2);
        b.next = new ListNode(9);
        b.next.next  = new ListNode(7);

        ListNode sum = addTwoNumbers(a, b);

        while(sum!=null) {
            System.out.print(sum.val + "->");
            sum = sum.next;
        }

    }

}
