package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 919. 完全二叉树插入器
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，结点数达到最大）的，并且所有的结点都尽可能地集中在左侧。
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 * CBTInserter(TreeNode root) 使用头结点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v) 将 TreeNode 插入到存在值为 node.val = v  的树中以使其保持完全二叉树的状态，
 * 并返回插入的 TreeNode 的父结点的值；
 * CBTInserter.get_root() 将返回树的头结点。
 *
 * 示例 1：
 * 输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * 输出：[null,1,[1,2]]
 *
 * 示例 2：
 * 输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 *
 * 提示：
 * 最初给定的树是完全二叉树，且包含 1 到 1000 个结点。
 * 每个测试用例最多调用 CBTInserter.insert  操作 10000 次。
 * 给定结点或插入结点的每个值都在 0 到 5000 之间。
 */
public class CompleteBinaryTreeInserter {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        CBTInserter cbt = new CBTInserter(root);
        System.out.println(cbt.insert(2));
        System.out.println(cbt.get_root());
    }

}

class CBTInserter {

    TreeNode root;
    Queue<TreeNode> queue;

    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode curr = queue.peek();
            if(curr.left==null||curr.right==null) {
                if(curr.left!=null) {
                    queue.offer(curr.left);
                }
                break;
            }else {
                queue.poll();
                queue.offer(curr.left);
                queue.offer(curr.right);
            }
        }
    }

    public int insert(int v) {
        TreeNode curr = queue.peek();
        if(curr.left==null) {
            curr.left = new TreeNode(v);
            queue.offer(curr.left);
        }else {
            queue.poll();
            curr.right = new TreeNode(v);
            queue.offer(curr.right);
        }
        return curr.val;
    }

    public TreeNode get_root() {
        return this.root;
    }

}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
