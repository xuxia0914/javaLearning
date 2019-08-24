package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null) {
            return l2;
        }
        if(l2==null) {
            return l1;
        }
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode res = new ListNode(0);
        ListNode curr = res;
        int carry = 0;
        int sum;
        while(l1!=null||l2!=null) {
            ListNode tmp;
            sum = 0;
            if(l1!=null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2!=null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum/10;
            sum = sum%10;
            tmp = new ListNode(sum);
            curr.next = tmp;
            curr = curr.next;
        }
        if(carry>0) {
            curr.next = new ListNode(carry);
        }
        return reverse(res.next);
    }

    public ListNode reverse(ListNode node) {
        if(node==null||node.next==null) {
            return node;
        }
        ListNode pre = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return pre;
    }

}
