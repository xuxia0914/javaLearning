package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.BinaryTreeNodeWithRightPointer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
 * 如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * 输出：[1,#,2,3,#,4,5,7,#]
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 *
 * 提示：
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
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
