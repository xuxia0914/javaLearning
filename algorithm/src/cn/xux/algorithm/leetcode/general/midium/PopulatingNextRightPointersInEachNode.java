package cn.xux.algorithm.leetcode.general.midium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 示例：
 * 输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 * 输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *
 * 提示：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 */
public class PopulatingNextRightPointersInEachNode {

    // O(1)空间
    public Node connect(Node root) {
        Node start = root;
        while(start!=null&&start.left!=null) {
            Node newStart = start.left;
            Node curr = start;
            Node pre = null;
            while(curr!=null) {
                if(pre!=null) {
                    pre.next = curr.left;
                }
                curr.left.next = curr.right;
                pre = curr.right;
                curr = curr.next;
            }
            start = newStart;
        }
        return root;
    }

    /**
     * 迭代
     * 执行用时 :1 ms, 在所有 Java 提交中击败了76.19%的用户
     * 内存消耗 :37.9 MB, 在所有 Java 提交中击败了48.79%的用户
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if(root==null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0;i<n;i++) {
                Node curr = queue.poll();
                if(i!=n-1) {
                    curr.next = queue.peek();
                }
                if(curr.left!=null) {
                    queue.offer(curr.left);
                    queue.offer(curr.right);
                }
            }
        }
        return root;
    }

    /**
     * 递归
     * 执行用时 :1 ms, 在所有 Java 提交中击败了76.19%的用户
     * 内存消耗 :38 MB, 在所有 Java 提交中击败了48.59%的用户
     * @param root
     * @return
     */
    public Node connect1(Node root) {
        if(root==null||root.left==null) {
            return root;
        }
        root.left.next = root.right;
        if(root.next!=null) {
            root.right.next = root.next.left;
        }
        connect1(root.left);
        connect1(root.right);
        return root;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
