package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110. 删点成林
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 *
 * 示例：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 *
 * 提示：
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 */
public class DeleteNodesAndReturnForest {

    Set<Integer> set = new HashSet<>();
    List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if(root==null) {
            return result;
        }
        for(int num : to_delete) {
            set.add(num);
        }
        dfs(root, true);
        return result;
    }

    public void dfs(TreeNode node, boolean isRoot) {
        if(node==null) {
            return;
        }
        if(isRoot) {
            if(!set.contains(node.val)) {
                result.add(node);
            }else {
                dfs(node.left, true);
                dfs(node.right, true);
                return;
            }
        }
        if(node.left!=null) {
            if(!set.contains(node.left.val)) {
                dfs(node.left, false);
            }else {
                dfs(node.left.left, true);
                dfs(node.left.right, true);
                node.left = null;
            }
        }
        if(node.right!=null) {
            if(!set.contains(node.right.val)) {
                dfs(node.right, false);
            }else {
                dfs(node.right.left, true);
                dfs(node.right.right, true);
                node.right = null;
            }
        }
    }

}
