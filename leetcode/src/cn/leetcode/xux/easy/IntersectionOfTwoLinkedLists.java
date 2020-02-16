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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) {
            return null;
        }
        int lenA = 0;
        int lenB = 0;
        ListNode currA = headA;
        while(currA!=null) {
            lenA++;
            currA = currA.next;
        }
        ListNode currB = headB;
        while(currB!=null) {
            lenB++;
            currB = currB.next;
        }
        currA = headA;
        currB = headB;
        while(lenA>lenB) {
            currA = currA.next;
            lenA--;
        }
        while(lenB>lenA) {
            currB = currB.next;
            lenB--;
        }
        while(currA!=null) {
            if(currA==currB) {
                return currA;
            }else {
                currA = currA.next;
                currB = currB.next;
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

}
