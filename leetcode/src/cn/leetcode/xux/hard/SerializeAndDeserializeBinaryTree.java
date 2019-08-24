package cn.leetcode.xux.hard;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树的序列化和去序列化
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * For example, you may serialize the following tree
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeAndDeserializeBinaryTree {

    public String serialize(BinaryTreeNode tree) {
        StringBuffer sb = new StringBuffer();
        if(tree==null) {
            sb.append("#");
        }else {
            sb.append(tree.val).append(",");
            sb.append(serialize(tree.left)).append(",");
            sb.append(serialize(tree.right));
        }
        return sb.toString();
    }

    public BinaryTreeNode deSerialize(String s) {
        LinkedList<String> queue = new LinkedList<String>();
        queue.addAll(Arrays.asList(s.split(",")));
        BinaryTreeNode tree = deSerialize(queue);
        return tree;
    }

    public BinaryTreeNode deSerialize(LinkedList<String> queue) {
        if(queue==null||queue.isEmpty()) {
            return null;
        }
        String curr = queue.poll();
        if("#".equals(curr)) {
            return null;
        }
        BinaryTreeNode tree = new BinaryTreeNode(Integer.valueOf(curr));
        tree.left = deSerialize(queue);
        tree.right = deSerialize(queue);
        return tree;
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
        a.deSerialize(s);
    }

}
