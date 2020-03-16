package cn.leetcode.xux.hard;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 示例:
 * 你可以将以下二叉树：
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 */
public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(BinaryTreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root==null) {
            return null;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append("[").append(root.val);
        while(!queue.isEmpty()) {
            BinaryTreeNode curr = queue.poll();
            if(curr.left!=null) {
                sb.append(",").append(curr.left.val);
                queue.offer(curr.left);
            }else {
                sb.append(",null");
            }
            if(curr.right!=null) {
                sb.append(",").append(curr.right.val);
                queue.offer(curr.right);
            }else {
                sb.append(",null");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {
        if(data==null||data.length()<=2) {
            return null;
        }
        String[] s = data.substring(1, data.length()-1).split(",");
        BinaryTreeNode root = new BinaryTreeNode(Integer.valueOf(s[0]));
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len = s.length;
        int i = 1;
        while(i<len) {
            BinaryTreeNode curr = queue.poll();
            if(!"null".equals(s[i])) {
                curr.left = new BinaryTreeNode(Integer.valueOf(s[i]));
                queue.offer(curr.left);
            }
            i++;
            if(!"null".equals(s[i])) {
                curr.right = new BinaryTreeNode(Integer.valueOf(s[i]));
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        node3.left = node4;
        node3.right = node5;
        node1.left = node2;
        node1.right = node3;
        SerializeAndDeserializeBinaryTree a = new SerializeAndDeserializeBinaryTree();
        String s = a.serialize(node1);
        System.out.println(s);
        a.deserialize(s);
    }

}
