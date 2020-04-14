package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.ListNode;

import java.util.Stack;

/**
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null||l2==null) {
            return l1==null?l2:l1;
        }
        Stack<Integer> stack1 =  new Stack<>();
        ListNode curr1 = l1;
        while(curr1!=null) {
            stack1.push(curr1.val);
            curr1 = curr1.next;
        }
        Stack<Integer> stack2 =  new Stack<>();
        ListNode curr2 = l2;
        while(curr2!=null) {
            stack2.push(curr2.val);
            curr2 = curr2.next;
        }
        int carry = 0;
        ListNode res = null;
        while(!stack1.isEmpty()||!stack2.isEmpty()) {
            if(!stack1.isEmpty()) {
                carry += stack1.pop();
            }
            if(!stack2.isEmpty()) {
                carry += stack2.pop();
            }
            ListNode curr = new ListNode(carry%10);
            carry /= 10;
            curr.next = res;
            res = curr;
        }
        if(carry==1) {
            ListNode curr = new ListNode(carry);
            curr.next = res;
            res = curr;
        }
        return res;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
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
