package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652. 寻找重复的子树
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 *
 * 示例 1：
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * 下面是两个重复的子树：
 *       2
 *      /
 *     4
 * 和
 *     4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 */
public class FindDuplicateSubtrees {

    List<TreeNode> res = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res.clear();
        helper(root);
        return res;
    }

    public String helper(TreeNode node) {
        if(node==null) {
            return "";
        }
        String route = "" + node.val+","+helper(node.left)+","+helper(node.right);
        if(map.containsKey(route)&&map.get(route)==1) {
            res.add(node);
        }
        map.put(route, map.getOrDefault(route, 0)+1);
        return route;
    }

    public static void main(String[] args) {
        FindDuplicateSubtrees fds = new FindDuplicateSubtrees();
        System.out.println(fds.findDuplicateSubtrees(new TreeNode(new Integer[]{1,2,3,4,null,2,4,null,null,4})));
    }

}
