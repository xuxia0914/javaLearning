package cn.leetcode.xux.easy;

import cn.leetcode.xux.common.BinaryTreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example:
 * Given the sorted array: [-10,-3,0,5,9],
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedArrayToBinarySearchTree {

    public BinaryTreeNode sortedArrayToBST(int[] nums) {
        BinaryTreeNode res;
        if(nums==null||nums.length==0) {
            return null;
        }
        int currIdx = nums.length/2;
        res = new BinaryTreeNode(nums[currIdx]);
        if(currIdx>0) {
            int[] leftNums = new int[currIdx];
            for(int i=0;i<currIdx;i++) {
                leftNums[i] = nums[i];
            }
            res.left = sortedArrayToBST(leftNums);
        }
        if(currIdx<nums.length-1) {
            int[] rightNums = new int[nums.length-1-currIdx];
            for(int i=currIdx+1;i<nums.length;i++) {
                rightNums[i-currIdx-1] = nums[i];
            }
            res.right = sortedArrayToBST(rightNums);
        }
        return res;
    }

}
