package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;
import cn.xux.algorithm.common.DoublyLinkedList;

import java.util.Stack;

/**
 * 426. 将二叉搜索树转为有序双向链表
 * Change the left and right pointers of each node in Binary search Tree to make it a sorted doubly linked list.
 * Method:
 * Just do the Post order traversal and while visiting the node,
 *     Point the left link of visiting node to right most node in left tree.
 *     Point the right link of visiting node to left most node in right tree.
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    //迭代
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

    /**
     * lintcode
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList1(TreeNode root) {
        // Write your code here.
        if(root==null) {
            return null;
        }
        TreeNode[] ans = helper(root);
        ans[0].left = ans[1];
        ans[1].right = ans[0];
        return ans[0];
    }

    public TreeNode[] helper(TreeNode root) {
        if(root==null) {
            return null;
        }
        TreeNode head = root;
        TreeNode tail = root;
        TreeNode[] leftAns = helper(root.left);
        TreeNode[] rightAns = helper(root.right);
        if(leftAns!=null) {
            leftAns[1].right = root;
            root.left = leftAns[1];
            head = leftAns[0];
        }
        if(rightAns!=null) {
            root.right = rightAns[0];
            rightAns[0].left = root;
            tail = rightAns[1];
        }
        return new TreeNode[]{head, tail};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{4,3,5,2,null,null,null,1,null});
        treeToDoublyList(root);
    }

}
