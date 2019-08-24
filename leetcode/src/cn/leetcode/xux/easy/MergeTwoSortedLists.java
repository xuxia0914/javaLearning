package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 * Example:
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedLists {

    public static ListNode solution1(ListNode node1, ListNode node2) {
        ListNode resultHead = new ListNode(0);
        ListNode resultTail = resultHead;
        while(node1!=null||node2!=null) {
            if(node1==null) {
                resultTail.next = node2;
                break;
            }
            if(node2==null) {
                resultTail.next = node1;
                break;
            }
            if(node1.val>node2.val) {
                resultTail.next = node2;
                resultTail = resultTail.next;
                node2 = node2.next;
            }else {
                resultTail.next = node1;
                resultTail = resultTail.next;
                node1 = node1.next;
            }
        }
        return resultHead.next;
    }

    public ListNode solution2(ListNode l1, ListNode l2) {
        if(l1==null) {
            return l2;
        }
        if(l2==null) {
            return l1;
        }
        ListNode res = new ListNode(0);
        if(l1.val<l2.val) {
            res.next = l1;
            res.next.next = solution2(l1.next, l2);
        }else {
            res.next = l2;
            res.next.next = solution2(l1, l2.next);
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        ListNode curr1 = node1;
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;
        ListNode curr2 = node4;
        while (curr1!=null) {
            System.out.print(curr1.val + "->");
            curr1 = curr1.next;
        }
        System.out.println("");
        while (curr2!=null) {
            System.out.print(curr2.val + "->");
            curr2 = curr2.next;
        }
        System.out.println("");
        ListNode reList = solution1(node1, node4);
        while (reList!=null) {
            System.out.print(reList.val + "->");
            reList = reList.next;
        }
    }

}
