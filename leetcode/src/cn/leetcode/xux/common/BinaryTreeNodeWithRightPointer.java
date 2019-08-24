package cn.leetcode.xux.common;

/**
 * 二叉树next指向右边的叶子，如果不存在指向null
 */
public class BinaryTreeNodeWithRightPointer {

    public int val;
    public BinaryTreeNodeWithRightPointer left;
    public BinaryTreeNodeWithRightPointer right;
    public BinaryTreeNodeWithRightPointer next;

    public BinaryTreeNodeWithRightPointer() {}

    public BinaryTreeNodeWithRightPointer(int _val) {
        val = _val;
    }

}
