package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.BinaryTreeNodeWithRightPointer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 */
public class PopulatingNextRightPointersInEachNodeTII {

    /**
     * 迭代
     * 执行用时 :4 ms, 在所有 Java 提交中击败了47.63%的用户
     * 内存消耗 :50.5 MB, 在所有 Java 提交中击败了99.10%的用户
     * @param root
     * @return
     */
    public BinaryTreeNodeWithRightPointer connect(BinaryTreeNodeWithRightPointer root) {
        if(root==null) {
            return null;
        }
        Queue<BinaryTreeNodeWithRightPointer> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0;i<n;i++) {
                BinaryTreeNodeWithRightPointer curr = queue.poll();
                if(i!=n-1) {
                    curr.next = queue.peek();
                }
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                if(curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
        }
        return root;
    }

    /**
     * 递归
     * 执行用时 :2 ms, 在所有 Java 提交中击败了96.47%的用户
     * 内存消耗 :61.4 MB, 在所有 Java 提交中击败了20.48%的用户
     * @param root
     * @return
     */
    public BinaryTreeNodeWithRightPointer connect1(BinaryTreeNodeWithRightPointer root) {
        if(root==null) {
            return null;
        }
        BinaryTreeNodeWithRightPointer next = findNext(root);
        if(root.left!=null) {
            if(root.right!=null) {
                root.left.next = root.right;
            }else {
                root.left.next = next;
            }
        }
        if(root.right!=null) {
            root.right.next = next;
        }
        connect1(root.right);
        connect1(root.left);
        return root;
    }

    BinaryTreeNodeWithRightPointer findNext(BinaryTreeNodeWithRightPointer root) {
        if(root.next==null) {
            return null;
        }
        while(root.next!=null) {
            if(root.next.left!=null) {
                return root.next.left;
            }
            if(root.next.right!=null) {
                return root.next.right;
            }
            root = root.next;
        }
        return null;
    }

}
