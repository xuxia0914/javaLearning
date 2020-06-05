package cn.leetcode.xux.general.midium;

import cn.leetcode.xux.common.TreeNode;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 *
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 *
 * 提示：
 * 给定的树是非空的，且最多有 K 个结点。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 */
public class AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        if(root==null||target==null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Map<TreeNode, TreeNode> map = new HashMap<>();
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left!=null) {
                map.put(curr.left, curr);
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                map.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }
        queue.clear();
        queue.offer(target);
        Set<TreeNode> set = new HashSet<>();
        set.add(target);
        while(K-->0&&!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                TreeNode curr = queue.poll();
                if(curr.left!=null&&set.add(curr.left)) {
                    queue.add(curr.left);
                }
                if(curr.right!=null&&set.add(curr.right)) {
                    queue.add(curr.right);
                }
                TreeNode parent = map.get(curr);
                if(parent!=null&&set.add(parent)) {
                    queue.add(parent);
                }
            }
        }
        while(!queue.isEmpty()) {
            result.add(queue.poll().val);
        }
        return result;
    }

}
