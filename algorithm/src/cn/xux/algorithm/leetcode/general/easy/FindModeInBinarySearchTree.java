package cn.xux.algorithm.leetcode.general.easy;

import cn.xux.algorithm.common.TreeNode;

import java.util.*;

/**
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class FindModeInBinarySearchTree {

    public int[] findMode(TreeNode root) {
        if(root==null) {
            return new int[]{};
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node!=null) {
            stack.push(node);
            node = node.left;
        }
        int max=0;
        int cnt=0;
        int pre = root.val;
        TreeNode curr;
        while(!stack.isEmpty()) {
            curr = stack.pop();
            if(curr.val==pre) {
                cnt++;
            }else {
                if(cnt>max) {
                    max = cnt;
                    list.clear();
                    list.add(pre);
                }else if(cnt==max) {
                    list.add(pre);
                }
                cnt = 1;
                pre = curr.val;
            }
            curr = curr.right;
            while(curr!=null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        if(cnt>max) {
            list.clear();
            list.add(pre);
        }else if(cnt==max) {
            list.add(pre);
        }
        int[] res = new int[list.size()];
        for(int i=0;i<list.size();i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        FindModeInBinarySearchTree f = new FindModeInBinarySearchTree();
        f.findMode(new TreeNode(new Integer[]{2,1,2}));
    }

}
