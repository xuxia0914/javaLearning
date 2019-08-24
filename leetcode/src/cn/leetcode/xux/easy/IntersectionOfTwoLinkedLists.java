package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 * Notes:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class IntersectionOfTwoLinkedLists {

    public static ListNode solution1(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
            return null;
        }
        int lenA = 0;
        int lenB = 0;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while(nodeA!=null) {
            lenA += 1;
            nodeA = nodeA.next;
        }
        while(nodeB!=null) {
            lenB += 1;
            nodeB = nodeB.next;
        }
        nodeA = headA;
        nodeB = headB;
        while(lenA>lenB) {
            nodeA = nodeA.next;
            lenA--;
        }
        while(lenB>lenA) {
            nodeB = nodeB.next;
            lenB--;
        }
        while(nodeA!=null&&nodeB!=null) {
            if(nodeA==nodeB) {
                return nodeA;
            }else {
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
        }
        return null;
    }

    public ListNode solution2(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        while(headA!=null) {
            set.add(headA);
            headA = headA.next;
        }
        while(headB!=null) {
            if(!set.add(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        ListNode node8 = new ListNode(8);

        node7.next = node8;
        node6.next = node7;
        node5.next = node6;
        node4.next = node5;
        node3.next = node4;
        node2.next = node6;
        node1.next = node2;
        ListNode curr1 = node1;
        while (curr1!=null) {
            System.out.print(curr1.val + "->");
            curr1 = curr1.next;
        }
        System.out.println("");
        ListNode curr2 = node3;
        while (curr2!=null) {
            System.out.print(curr2.val + "->");
            curr2 = curr2.next;
        }
        System.out.println("");
        ListNode reList = solution1(node1, node3);
        while (reList!=null) {
            System.out.print(reList.val + "->");
            reList = reList.next;
        }
    }

}
