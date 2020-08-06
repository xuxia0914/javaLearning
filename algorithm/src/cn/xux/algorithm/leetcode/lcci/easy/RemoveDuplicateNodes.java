package cn.xux.algorithm.leetcode.lcci.easy;

import cn.xux.algorithm.common.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 面试题 02.01. 移除重复节点
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 *
 * 示例2:
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 *
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 *
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 */
public class RemoveDuplicateNodes {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        new RemoveDuplicateNodes().removeDuplicateNodes(head);
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode pre = new ListNode(0);
        ListNode curr = head;
        Set<Integer> set = new HashSet<>();
        while(pre!=null) {
            while(curr!=null&&set.contains(curr.val)) {
                curr = curr.next;
            }
            pre.next = curr;
            pre = curr;
            if(curr!=null) {
                set.add(curr.val);
                curr = curr.next;
            }
        }
        return head;
    }

}
