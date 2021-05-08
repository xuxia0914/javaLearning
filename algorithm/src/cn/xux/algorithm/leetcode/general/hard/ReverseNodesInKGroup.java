package cn.xux.algorithm.leetcode.general.hard;

import cn.xux.algorithm.common.ListNode;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 示例 3：
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * 示例 4：
 * 输入：head = [1], k = 1
 * 输出：[1]
 *
 * 提示：
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */
public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode node, int k) {
        if(node==null) {
            return null;
        }
        int n = k;
        ListNode pre = node;
        while(k>1&&pre.next!=null) {
            pre = pre.next;
            k--;
        }
        if(k>1) {
            return node;
        }
        ListNode behind = pre.next;
        pre.next = null;

        ListNode node1 = ListNode.reverse1(node);
        ListNode node2 = node1;
        while(node2.next!=null) {
            node2 = node2.next;
        }
        node2.next = reverseKGroup(behind, n);
        return node1;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node4.next = node5;
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        ListNode result = new ReverseNodesInKGroup().reverseKGroup(node1, 3);
        while(result!=null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

}
