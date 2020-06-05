package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1305. 两棵二叉搜索树中的所有元素
 * 给你 root1 和 root2 这两棵二叉搜索树。
 * 请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 *
 * 示例 1：
 * 输入：root1 = [2,1,4], root2 = [1,0,3]
 * 输出：[0,1,1,2,3,4]
 *
 * 示例 2：
 * 输入：root1 = [0,-10,10], root2 = [5,1,7,0,2]
 * 输出：[-10,0,0,1,2,5,7,10]
 *
 * 示例 3：
 * 输入：root1 = [], root2 = [5,1,7,0,2]
 * 输出：[0,1,2,5,7]
 *
 * 示例 4：
 * 输入：root1 = [0,-10,10], root2 = []
 * 输出：[-10,0,10]
 *
 * 示例 5：
 * 输入：root1 = [1,null,8], root2 = [8,1]
 * 输出：[1,1,8,8]
 *
 * 提示：
 * 每棵树最多有 5000 个节点。
 * 每个节点的值在 [-10^5, 10^5] 之间。
 */
public class AllElementsInTwoBinarySearchTrees {

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode curr1 = root1;
        while(curr1!=null) {
            stack1.push(curr1);
            curr1 = curr1.left;
        }
        TreeNode curr2 = root2;
        while(curr2!=null) {
            stack2.push(curr2);
            curr2 = curr2.left;
        }
        while(!stack1.isEmpty()||!stack2.isEmpty()) {
            if((!stack1.isEmpty()&&!stack2.isEmpty()&&stack1.peek().val<=stack2.peek().val)
                    ||stack2.isEmpty()) {
                curr1 = stack1.pop();
                result.add(curr1.val);
                TreeNode right = curr1.right;
                while(right!=null) {
                    stack1.push(right);
                    right = right.left;
                }
            }else {
                curr2 = stack2.pop();
                result.add(curr2.val);
                TreeNode right = curr2.right;
                while(right!=null) {
                    stack2.push(right);
                    right = right.left;
                }
            }
        }
        return result;
    }

}
