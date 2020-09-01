package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 708. 循环有序列表的插入
 * 给定循环升序列表中的一个点，写一个函数向这个列表中插入一个新元素，使这个列表仍然是循环升序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），你需要创建一个循环有序列表并返回这个点。
 * 否则。请返回原先给定的节点。
 *
 * 下面的例子可以帮你更好的理解这个问题：
 * 在这里插入图片描述
 *
 * 在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2。
 * 在这里插入图片描述
 *
 * 新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3。
 */
public class InsertIntoASortedCircularLinkedList {

    public Node insert(Node head, int insertVal) {
        if(head==null) {
            Node node = new Node();
            node.val = insertVal;
            node.next = node;
        }
        if(head.next==head) {
            Node node = new Node(insertVal, head);
            head.next = node;
            return head;
        }
        Node curr = head;
        while(curr.next!=head) {
            if((curr.val<=curr.next.val&&insertVal>=curr.val&&insertVal<=curr.next.val)
                ||(curr.val>curr.next.val&&insertVal>curr.val)) {
                Node node = new Node(insertVal, curr.next);
                curr.next = node;
                return head;
            }
            curr = curr.next;
        }
        Node node = new Node(insertVal, curr.next);
        curr.next = node;
        return head;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    }

}
