package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 272 二叉搜索树中最接近的值 II
 * 给定一棵非空二叉搜索树以及一个target值，找到 BST 中最接近给定值的 k 个数。
 *
 * 样例
 * 样例 1:
 *
 * 输入: {1}, 0.000000, 1
 * 输出: [1]
 * 解释： 二叉树 {1}，表示如下的树结构：
 *  1
 *
 * 样例 2:
 * 输入: {3,1,4,#,2}, 0.275000, 2
 * 输出: [1,2]
 * 解释：二叉树 {3,1,4,#,2}，表示如下的树结构：
 *   3
 *  /  \
 * 1    4
 *  \
 *   2
 *
 * 挑战
 * 假设是一棵平衡二叉搜索树，你可以用时间复杂度低于O(n)的算法解决问题吗( n 为节点个数)？
 *
 * 注意事项
 * 给出的target值为浮点数
 * 你可以假设 k 总是合理的，即 k ≤ 总节点数
 * 我们可以保证给出的 BST 中只有唯一一个最接近给定值的 k 个值的集合
 */
public class ClosestBinarySearchTreeValueII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ans = new ArrayList<>();
        if(root==null||k<1) {
            return ans;
        }
        List<Integer> inOrder = new ArrayList<>();
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<>();
        while(curr!=null) {
            stack.push(curr);
            curr = curr.left;
        }
        while(!stack.isEmpty()) {
            curr = stack.pop();
            inOrder.add(curr.val);
            curr = curr.right;
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        int left = 0;
        double min = Math.max(Math.abs(target-inOrder.get(left)),
                Math.abs(target-inOrder.get(left+k-1)));
        for(int i=1;i+k-1<inOrder.size();i++) {
            double currMin = Math.max(Math.abs(target-inOrder.get(i)),
                    Math.abs(target-inOrder.get(i+k-1)));
            if(currMin<min) {
                left = i;
                min = currMin;
            }
        }
        for(int i=0;i<k;i++) {
            ans.add(left+i);
        }
        return ans;
    }

}
