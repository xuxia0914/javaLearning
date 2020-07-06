package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.ListNode;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class ReverseLinkedListII {

    //迭代
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while(head!=null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    //递归
    public ListNode reverseList1(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode pre = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return pre;
    }

    public static ListNode solution(ListNode node, int m, int n) {
        ListNode result = new ListNode(0);
        result.next = node;
        ListNode left = result;
        for(int i=0;i<m-1;i++) {
            left = left.next;
        }
        ListNode right = left.next;
        for(int i=0;i<n-m;i++) {
            ListNode tmp = left.next;
            left.next = right.next;
            right.next = right.next.next;
            left.next.next = tmp;
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        ListNode result = solution(node1, 1, 6);
        while(result!=null) {
            System.out.print(result.val+"->");
            result = result.next;
        }
    }

}
