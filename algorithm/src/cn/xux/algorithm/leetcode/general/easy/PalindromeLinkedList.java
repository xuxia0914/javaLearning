package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.ListNode;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        if(head==null) {
            return true;
        }
        int length = 0;
        ListNode node1 = head;
        while(node1!=null) {
            length++;
            node1=node1.next;
        }
        int mid = (length-1)/2+1;
        ListNode node2 = head;
        while(mid>0) {
            node2 = node2.next;
            mid--;
        }
        ListNode node3 = ListNode.reverse2(node2);
        ListNode node4 = head;
        while(node3!=null) {
            if(node3.val!=node4.val) {
                return false;
            }
            node3 = node3.next;
            node4 = node4.next;
        }
        return true;
    }

}
