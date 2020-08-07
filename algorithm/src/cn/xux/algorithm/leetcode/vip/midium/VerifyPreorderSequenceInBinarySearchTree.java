package cn.xux.algorithm.leetcode.vip.midium;

import java.util.Stack;

/**
 * 255. 验证前序遍历序列二叉搜索树
 * 给定一个整数数组，你需要验证它是否是一个二叉搜索树正确的先序遍历序列。
 * 你可以假定该序列中的数都是不相同的。
 * 参考以下这颗二叉搜索树：
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 * 输入: [5,2,6,1,3]
 * 输出: false
 *
 * 示例 2：
 * 输入: [5,2,1,3,6]
 * 输出: true
 *
 * 进阶挑战：
 * 您能否使用恒定的空间复杂度来完成此题？
 */
public class VerifyPreorderSequenceInBinarySearchTree {

    //单调栈
    public boolean verifyPreorder(int[] preorder) {
        if(preorder==null||preorder.length<3) {
            return true;
        }
        int min = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int num : preorder) {
            if(num<min) {
                return false;
            }
            while(!stack.isEmpty()&&stack.peek()<num) {
                min = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }

    public boolean verifyPreorder1(int[] preorder) {
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
