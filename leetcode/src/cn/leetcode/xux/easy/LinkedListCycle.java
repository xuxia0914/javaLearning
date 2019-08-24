package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 *Given a linked list, determine if it has a cycle in it.
 * Follow up: Can you solve it without using extra space?
 * 如何判断一个单链表中是否有环？
 */
public class LinkedListCycle {

    public static boolean solution1(ListNode header) {
        if(header==null) {
            return false;
        }
        ListNode slow = header;
        ListNode fast = header;
        while(fast!=null&&fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) {
                return true;
            }
        }
        return false;
    }

    public static boolean solution2(ListNode header) {
        Set<ListNode> set = new HashSet<ListNode>();
        while(header!=null) {
            if(!set.add(header)) {
                return true;
            }
            header = header.next;
        }
        return false;
    }

}
