package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 366. 寻找完全二叉树的叶子节点
 * 给你一棵完全二叉树，请按以下要求的顺序收集它的全部节点：
 * 依次从左到右，每次收集并删除所有的叶子节点
 * 重复如上过程直到整棵树为空
 * 示例:
 * 输入: [1,2,3,4,5]
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 * 输出: [[4,5,3],[2],[1]]
 * 解释:
 * 删除叶子节点 [4,5,3] ，得到如下树结构：
 *   1
 *  /
 * 2
 * 现在删去叶子节点 [2] ，得到如下树结构：
 * 1
 * 现在删去叶子节点 [1] ，得到空树：
 * []
 */
public class FindLeavesOfBinaryTree {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null) {
            return ans;
        }
        int hei = dfs(root);
        for(int i=1;i<=hei;i++) {
            ans.add(map.get(i));
        }
        return ans;
    }

    Map<Integer, List<Integer>> map = new HashMap<>();

    //返回自底向上的最大高度
    private int dfs(TreeNode node) {
        if(node==null) {
            return 0;
        }
        int hei = 0;
        int leftHei = dfs(node.left);
        int rightHei = dfs(node.right);
        hei = Math.max(leftHei, rightHei)+1;
        if(!map.containsKey(hei)) {
            map.put(hei, new ArrayList<>());
        }
        map.get(hei).add(node.val);
        return hei;
    }

}
