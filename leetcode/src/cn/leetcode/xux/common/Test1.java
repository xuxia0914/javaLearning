package cn.leetcode.xux.common;

import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
//        System.out.println(new Test1().getAns(new int[]{8,9,7,3,0,5,11}));
    }

    /**
     * 95. 不同的二叉搜索树 II
     * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
     *
     * 示例：
     * 输入：3
     * 输出：
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * 解释：
     * 以上的输出对应以下 5 种不同结构的二叉搜索树：
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     *
     * 提示：0 <= n <= 8
     */
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int left, int right) {
        List<TreeNode> ans = new ArrayList<>();
        if(left>right) {
            return ans;
        }
        for(int i=left;i<=right;i++) {
            List<TreeNode> leftAns = dfs(left, i-1);
            List<TreeNode> rightAns = dfs(i+1, right);
            for(TreeNode l : leftAns) {
                for(TreeNode r : rightAns) {
                    TreeNode curr = new TreeNode(i);
                    curr.left = l;
                    curr.right = r;
                    ans.add(curr);
                }
            }
        }
        return ans;
    }

}