package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.TreeNode;
import cn.leetcode.xux.common.DoublyLinkedList;

import java.util.Stack;

/**
 * 将二叉搜索树转为有序双向链表
 * Change the left and right pointers of each node in Binary search Tree to make it a sorted doubly linked list.
 * Method:
 * Just do the Post order traversal and while visiting the node,
 *     Point the left link of visiting node to right most node in left tree.
 *     Point the right link of visiting node to left most node in right tree.
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    public static DoublyLinkedList treeToDoublyList(TreeNode root) {
        if(root==null) {
            return null;
        }
        DoublyLinkedList resHead = new DoublyLinkedList(0);
        DoublyLinkedList resTail = resHead;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode left = root;
        while(left!=null) {
            stack.push(left);
            left = left.left;
        }
        TreeNode curr, tmp;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            DoublyLinkedList newNode = new DoublyLinkedList(curr.val);
            newNode.pre = resTail;
            resTail.next = newNode;
            resTail = resTail.next;

            tmp = curr.right;
            while(tmp!=null) {
                stack.push(tmp);
                tmp = tmp.left;
            }
        }
        resHead.next.pre = null;
        return resHead.next;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(new Integer[]{4,3,5,2,null,null,null,1,null});
        treeToDoublyList(root);

    }

}
