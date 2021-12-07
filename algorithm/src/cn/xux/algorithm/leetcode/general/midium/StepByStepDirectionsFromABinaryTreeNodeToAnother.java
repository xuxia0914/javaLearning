package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2096. 从二叉树一个节点到另一个节点每一步的方向
 * 给你一棵 二叉树 的根节点 root ，这棵二叉树总共有 n 个节点。每个节点的值为 1 到 n 中的一个整数，且互不相同。给你一个整数 startValue ，表示起点节点 s 的值，和另一个不同的整数 destValue ，表示终点节点 t 的值。
 * <p>
 * 请找到从节点 s 到节点 t 的 最短路径 ，并以字符串的形式返回每一步的方向。每一步用 大写 字母 'L' ，'R' 和 'U' 分别表示一种方向：
 * <p>
 * 'L' 表示从一个节点前往它的 左孩子 节点。
 * 'R' 表示从一个节点前往它的 右孩子 节点。
 * 'U' 表示从一个节点前往它的 父 节点。
 * 请你返回从 s 到 t 最短路径 每一步的方向。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * 输出："UURL"
 * 解释：最短路径为：3 → 1 → 5 → 2 → 6 。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1], startValue = 2, destValue = 1
 * 输出："L"
 * 解释：最短路径为：2 → 1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目为 n 。
 * 2 <= n <= 105
 * 1 <= Node.val <= n
 * 树中所有节点的值 互不相同 。
 * 1 <= startValue, destValue <= n
 * startValue != destValue
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {

    public String getDirections(TreeNode root, int startValue, int destValue) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", root));
        String start = null;
        String dest = null;
        while (start == null || dest == null) {
            Node curr = queue.poll();
            if (start == null && curr.treeNode.val == startValue) {
                start = curr.dir;
            }
            if (dest == null && curr.treeNode.val == destValue) {
                dest = curr.dir;
            }
            if (curr.treeNode.left != null) {
                queue.offer(new Node(curr.dir + 'L', curr.treeNode.left));
            }
            if (curr.treeNode.right != null) {
                queue.offer(new Node(curr.dir + 'R', curr.treeNode.right));
            }
        }
        int i = 0;
        int j = 0;
        while (i < start.length() && j < dest.length()
                && start.charAt(i) == dest.charAt(j)) {
            i++;
            j++;
        }
        int k = start.length() - i;
        StringBuilder ans = new StringBuilder();
        while (k-- > 0) {
            ans.append('U');
        }
        ans.append(dest.substring(j));
        return ans.toString();
    }

    class Node {

        String dir;
        TreeNode treeNode;

        Node(String dir, TreeNode treeNode) {
            this.dir = dir;
            this.treeNode = treeNode;
        }

    }

}
