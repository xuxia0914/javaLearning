package cn.leetcode.xux.midium;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 430. 扁平化多级双向链表
 * 您将获得一个双向链表，除了下一个和前一个指针之外，它还有一个子指针，可能指向单独的双向链表
 * 这些子列表可能有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。
 * 扁平化列表，使所有结点出现在单级双链表中。您将获得列表第一级的头部。
 *
 * 示例:
 * 输入:
 *  1---2---3---4---5---6--NULL
 *          |
 *          7---8---9---10--NULL
 *              |
 *              11--12--NULL
 * 输出:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL
 */
public class FlattenAMultilevelDoublyLinkedList {

    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了5.90%的用户
     * 内存消耗 :43.1 MB, 在所有 Java 提交中击败了57.20%的用户
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        if(head==null) {
            return head;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node pre = null;
        while(!stack.isEmpty()) {
            Node curr = stack.pop();
            if(curr.next!=null) {
                stack.push(curr.next);
            }
            if(curr.child!=null) {
                stack.push(curr.child);
            }
            curr.prev = pre;
            if(pre!=null) {
                pre.next = curr;
                pre.child = null;
            }
            pre = curr;
        }
        return head;
    }

    /**
     * 执行用时 :2 ms, 在所有 Java 提交中击败了20.66%的用户
     * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了75.67%的用户
     */
    Queue<Node> queue = new LinkedList<>();
    public Node flatten1(Node head) {
        if(head==null) {
            return head;
        }
        helper(head);
        Node pre = queue.poll();
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            pre.next = curr;
            curr.prev = pre;
            pre.child = null;
            pre = pre.next;
        }
        return head;
    }

    void helper(Node head) {
        if(head==null) {
            return;
        }
        queue.offer(head);
        helper(head.child);
        helper(head.next);
    }

}

// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}
