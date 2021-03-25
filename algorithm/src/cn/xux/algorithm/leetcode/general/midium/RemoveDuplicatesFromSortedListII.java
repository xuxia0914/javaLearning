package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，
 * 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * 返回同样按升序排列的结果链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class RemoveDuplicatesFromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode curr = newHead;
        int pre = 0;
        boolean flag = false;   //标记pre有效性
        while(curr.next!=null) {
            if(flag&&curr.next.val==pre) {
                curr.next = curr.next.next;
            }else if(curr.next.next!=null&&curr.next.val==curr.next.next.val) {
                flag=true;
                pre = curr.next.val;
                curr.next = curr.next.next.next;
            }else {
                curr = curr.next;
            }
        }
        return newHead.next;
    }

}
