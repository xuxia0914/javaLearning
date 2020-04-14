package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 */
public class DeleteNodeInABst {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{28,12,45,4,24,35,47,2,9,14,25,31,42,46,48,0,3,8,11,13,20,null,26,30,33,41,43,null,null,null,49,null,1,null,null,7,null,10,null,null,null,17,22,null,27,29,null,32,34,36,null,null,44,null,null,null,null,6,null,null,null,16,18,21,23,null,null,null,null,null,null,null,null,null,37,null,null,5,null,15,null,null,19,null,null,null,null,null,40,null,null,null,null,null,null,39,null,38});
        new DeleteNodeInABst().deleteNode(root, 5);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(0);
        TreeNode parent = newRoot;
        parent.left = root;
        TreeNode curr = root;
        Boolean isLeft = true;
        while(curr!=null) {
            if(curr.val==key) {
                break;
            }else if(curr.val>key) {
                parent = curr;
                curr = curr.left;
                isLeft = true;
            }else {
                parent = curr;
                curr = curr.right;
                isLeft = false;
            }
        }
        TreeNode next = null;
        if(curr!=null) {
            if(curr.left!=null) {
                next = curr.left;
                TreeNode combine = next;
                while(combine.right!=null) {
                    combine = combine.right;
                }
                combine.right = curr.right;
            }else if(curr.right!=null) {
                next = curr.right;
                TreeNode combine = next;
                while(combine.left!=null) {
                    combine = combine.left;
                }
                combine.left = curr.left;
            }
        }
        if(isLeft) {
            parent.left = next;
        }else {
            parent.right = next;
        }
        return newRoot.left;
    }

}
