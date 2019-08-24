package cn.leetcode.xux.midium;


import cn.leetcode.xux.common.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthNodeFromEndOfList {

    /**
     * 这里需要扫描三趟， 翻转-删除-翻转
     * @param node
     * @param n
     * @return
     */
    public static ListNode solution1(ListNode node, int n) {
        if(null==node) {
            return null;
        }
        ListNode ln = ListNode.reverse1(node);
        int i=0;
        ListNode curr = ln;
        if(n==1) {
            return ListNode.reverse1(curr.next);
        }
        while(curr.next!=null&&i<n-2) {
            curr = curr.next;
            i++;
        }
        if(curr.next==null) {
            return ListNode.reverse1(ln);
        }
        curr.next = curr.next.next;
        return ListNode.reverse1(ln);
    }

    public static ListNode solution2(ListNode head, int n) {
        ListNode right = head;
        ListNode left = head;
        for(int i = 0; i < n; i++) {
            right = right.next;
            if(right==null&&i<n-1) {
                return head;
            }
        }
        if(right == null) {
            head = head.next;
            return head;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode curr = node1;
        while (curr!=null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println("");
        ListNode reList1 = solution1(node1, 5);
        while (reList1!=null) {
            System.out.print(reList1.val + "->");
            reList1 = reList1.next;
        }
        System.out.println("");
        ListNode reList2 = solution2(node1, 5);
        while (reList2!=null) {
            System.out.print(reList2.val + "->");
            reList2 = reList2.next;
        }

    }

}
