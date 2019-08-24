package cn.leetcode.xux.midium;

import cn.leetcode.xux.common.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a', a value of 1 represents 'b', and so on.
 * Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.
 * (As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".  A leaf of a node is a node that has no children.)
 * Example 1:
 * Input: [0,1,2,3,4,3,4]
 * Output: "dba"
 * Example 2:
 * Input: [25,1,3,1,3,0,2]
 * Output: "adz"
 * Example 3:
 * Input: [2,2,1,null,1,0,null,0]
 * Output: "abc"
 * Note:
 * The number of nodes in the given tree will be between 1 and 8500.
 * Each node in the tree will have a value between 0 and 25.
 */
public class SmallestStringStartingFromLeaf {

    public String smallestFromLeaf(BinaryTreeNode root) {
        if(root==null) {
            return "";
        }
        List<String> list = new ArrayList<>();
        listAll(list, root, "");
        String res = list.get(0);
        for(String s : list) {
            if(!compare(res, s)) {
                res = s;
            }
        }
        return new StringBuilder(res).reverse().toString();
    }

    public void listAll(List<String> list, BinaryTreeNode root, String curr) {
        if(root.left==null&&root.right==null) {
            list.add(curr+(char)(root.val+'a'));
            return;
        }
        if(root.left!=null) {
            listAll(list, root.left, curr+(char)(root.val+'a'));
        }
        if(root.right!=null) {
            listAll(list, root.right, curr+(char)(root.val+'a'));
        }
    }

    public boolean compare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int i=0;
        while(i<len1&&i<len2) {
            if(s1.charAt(len1-1-i)<s2.charAt(len2-1-i)) {
                return true;
            }
            if(s1.charAt(len1-1-i)>s2.charAt(len2-1-i)) {
                return false;
            }
            i++;
        }
        if(len1<=len2) {
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        SmallestStringStartingFromLeaf s = new SmallestStringStartingFromLeaf();
        System.out.println(s.smallestFromLeaf(new BinaryTreeNode(new Integer[]{25,1,null,0,0,1,null,null,null,0})));
    }

}
