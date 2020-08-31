package cn.xux.algorithm.leetcode.vip.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 663. 均匀树划分
 * 给定一棵有 n 个结点的二叉树，你的任务是检查是否可以通过去掉树上的一条边将树分成两棵，且这两棵树结点之和相等。
 * 样例 1:
 * 输入:
 *     5
 *    / \
 *   10 10
 *     /  \
 *    2   3
 * 输出: True
 * 解释:
 *     5
 *    /
 *   10
 *
 * 和: 15
 *
 *    10
 *   /  \
 *  2    3
 *
 * 和: 15
 *
 *
 * 样例 2:
 * 输入:
 *     1
 *    / \
 *   2  10
 *     /  \
 *    2   20
 * 输出: False
 * 解释: 无法通过移除一条树边将这棵树划分成结点之和相等的两棵子树。
 *
 * 注释 :
 * 树上结点的权值范围 [-100000, 100000]。
 * 1 <= n <= 10000
 */
public class EqualTreePartition {

    public boolean checkEqualTree(TreeNode root) {
        // write your code here
        this.root = root;
        int total = sum(root);
        return total%2==0&&set.contains(total/2);
    }

    TreeNode root;
    Set<Integer> set = new HashSet<>();

    public int sum(TreeNode node) {
        if(node==null) {
            return 0;
        }
        int ans = node.val+sum(node.left)+sum(node.right);
        if(node!=root) {
            set.add(ans);
        }
        return ans;
    }

}
