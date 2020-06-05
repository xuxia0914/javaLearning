package cn.leetcode.xux.general.midium;

/**
 * 255 Verify Preorder Sequence in Binary Search Tree 验证二叉搜索树的先序序列
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * You may assume each number in the sequence is unique.
 * Follow up:
 * Could you do it using only constant space complexity?
 */
public class VerifyPreorderSequenceInBinarySearchTree {

    public boolean verifyPreorder(int[] preorder) {
        if(preorder==null||preorder.length<3) {
            return true;
        }
        return verifyPreorder(preorder, 0, preorder.length-1);
    }

    public boolean verifyPreorder(int[] preorder, int start, int end) {
        if(end-start<2) {
            return true;
        }
        int mid = preorder[start];
        boolean flag = false;
        int preEnd = end;
        for(int i=start+1;i<=end;i++) {
            if(!flag) {
                if(preorder[i]>mid) {
                    flag = true;
                    preEnd = i-1;
                }
            }else {
                if(preorder[i]<mid) {
                    return false;
                }
            }
        }
        return verifyPreorder(preorder, start+1, preEnd)&&verifyPreorder(preorder, preEnd+1, end);
    }

    public static void main(String[] args) {
        VerifyPreorderSequenceInBinarySearchTree v = new VerifyPreorderSequenceInBinarySearchTree();
        System.out.println(v.verifyPreorder(new int[]{5,3,2,1,4,8,7,9}));
        System.out.println(v.verifyPreorder(new int[]{5,3,2,1,4,8,7,9,0}));
    }

}
