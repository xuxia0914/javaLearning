package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 449. 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，
 * 以便稍后在同一个或另一个计算机环境中重建。
 *
 * 设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。
 * 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 *
 * 编码的字符串应尽可能紧凑。
 *
 * 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
 */
public class SerializeAndDeserializeBst {

    public static void main(String[] args) {
        BinaryTreeNode node1 = new BinaryTreeNode(5);
        BinaryTreeNode node2 = new BinaryTreeNode(3);
        BinaryTreeNode node3 = new BinaryTreeNode(6);
        BinaryTreeNode node4 = new BinaryTreeNode(2);
        BinaryTreeNode node5 = new BinaryTreeNode(4);
        BinaryTreeNode node6 = new BinaryTreeNode(1);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        Codec codec = new Codec();
        System.out.println(codec.serialize(node1));
        System.out.println(codec.deserialize(codec.serialize(node1)));
    }

}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(BinaryTreeNode root) {
        if(root==null) {
            return "null";
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        while(!queue.isEmpty()) {
            BinaryTreeNode curr = queue.poll();
            if(curr.left==null) {
                sb.append(",null");
            }else {
                sb.append(",").append(curr.left.val);
                queue.offer(curr.left);
            }
            if(curr.right==null) {
                sb.append(",null");
            }else {
                sb.append(",").append(curr.right.val);
                queue.offer(curr.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {
        if(data==null||"null".equals(data)) {
            return null;
        }
        String[] strs = data.split(",");
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        int idx = 1;
        BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(strs[0]));
        queue.offer(root);
        while(idx<strs.length&&!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                BinaryTreeNode curr = queue.poll();
                if(idx<strs.length&&!"null".equals(strs[idx++])) {
                    BinaryTreeNode left = new BinaryTreeNode(Integer.parseInt(strs[idx-1]));
                    curr.left = left;
                    queue.offer(left);
                }
                if(idx<strs.length&&!"null".equals(strs[idx++])) {
                    BinaryTreeNode right = new BinaryTreeNode(Integer.parseInt(strs[idx-1]));
                    curr.right = right;
                    queue.offer(right);
                }
            }
        }
        return root;
    }

}