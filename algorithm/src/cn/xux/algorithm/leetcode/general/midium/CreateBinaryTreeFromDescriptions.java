package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2196. 根据描述创建二叉树
 * 给你一个二维整数数组 descriptions ，
 * 其中 descriptions[i] = [parenti, childi, isLefti]
 * 表示 parenti 是 childi 在 二叉树 中的 父节点，二叉树中各节点的值 互不相同 。此外：
 * <p>
 * 如果 isLefti == 1 ，那么 childi 就是 parenti 的左子节点。
 * 如果 isLefti == 0 ，那么 childi 就是 parenti 的右子节点。
 * 请你根据 descriptions 的描述来构造二叉树并返回其 根节点 。
 * <p>
 * 测试用例会保证可以构造出 有效 的二叉树。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：descriptions = [[20,15,1],[20,17,0],[50,20,1],[50,80,0],[80,19,1]]
 * 输出：[50,20,80,15,17,19]
 * 解释：根节点是值为 50 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：descriptions = [[1,2,1],[2,3,0],[3,4,1]]
 * 输出：[1,2,null,null,3,4]
 * 解释：根节点是值为 1 的节点，因为它没有父节点。
 * 结果二叉树如上图所示。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= descriptions.length <= 104
 * descriptions[i].length == 3
 * 1 <= parenti, childi <= 105
 * 0 <= isLefti <= 1
 * descriptions 所描述的二叉树是一棵有效二叉树
 */
public class CreateBinaryTreeFromDescriptions {

    public static void main(String[] args) {
        new CreateBinaryTreeFromDescriptions().createBinaryTree(
                new int[][]{{20, 15, 1}, {20, 17, 0}, {50, 20, 1}, {50, 80, 0}, {80, 19, 1}}
        );
    }

    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, int[]> map = new HashMap<>();
        Set<Integer> noRoot = new HashSet<>();
        for (int[] des : descriptions) {
            int[] children = map.getOrDefault(des[0], new int[2]);
            noRoot.add(des[1]);
            children[des[2] == 1 ? 0 : 1] = des[1];
            map.put(des[0], children);
        }
        int root = 0;
        for (int p : map.keySet()) {
            if (!noRoot.contains(p)) {
                root = p;
                break;
            }
        }
        TreeNode ans = new TreeNode(root);
        dfs(map, ans);
        return ans;
    }

    private void dfs(Map<Integer, int[]> map, TreeNode curr) {
        if (!map.containsKey(curr.val)) {
            return;
        }
        int[] children = map.get(curr.val);
        if (children[0] != 0) {
            curr.left = new TreeNode(children[0]);
            dfs(map, curr.left);
        }
        if (children[1] != 0) {
            curr.right = new TreeNode(children[1]);
            dfs(map, curr.right);
        }
    }

}
