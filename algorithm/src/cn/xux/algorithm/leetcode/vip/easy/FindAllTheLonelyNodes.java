package cn.xux.algorithm.leetcode.vip.easy;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1469. 寻找所有的独生节点
 * 二叉树中，如果一个节点是其父节点的唯一子节点，则称这样的节点为 “独生节点” 。
 * 二叉树的根节点不会是独生节点，因为它没有父节点。
 * 给定一棵二叉树的根节点 root ，返回树中 所有的独生节点的值所构成的数组 。
 * 数组的顺序 不限 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,4]
 * 输出：[4]
 * 解释：浅蓝色的节点是唯一的独生节点。
 * 节点 1 是根节点，不是独生的。
 * 节点 2 和 3 有共同的父节点，所以它们都不是独生的。
 *
 * 提示：
 * tree 中节点个数的取值范围是 [1, 1000]。
 * 每个节点的值的取值范围是 [1, 10^6]。
 */
public class FindAllTheLonelyNodes {

    List<Integer> ans = new ArrayList<>();

    public List<Integer> getLonelyNodes(TreeNode tree) {
        if(tree==null) {
            return null;
        }
        if(tree.left!=null&&tree.right==null) {
            ans.add(tree.left.val);
        }else if(tree.left==null&&tree.right!=null) {
            ans.add(tree.right.val);
        }
        getLonelyNodes(tree.left);
        getLonelyNodes(tree.right);
        return ans;
    }

}
