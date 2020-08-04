package cn.leetcode.xux.lintcode;

import cn.leetcode.xux.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1762. 往上爬
 * cat-only-icon
 * CAT 专属题目
 * 中文English
 * 给定一棵二叉树和Alice和Bob所在的节点a和b，每回合Alice和Bob可以沿着树的一条边走一步，
 * 当某个人走到另一个人的父结点上时，另一个人就输了。现在Alice先走，请Alice是否能赢?
 *
 * 样例
 * 样例 1:
 *
 * 输入:
 * {1,#,2,3}
 * 3
 * 1
 * 输出:
 * false
 *
 * 解释:
 *    1
 *     \
 *      2
 *     /
 *    3
 * 3 1
 * Alice爬到节点2，
 * 到Bob，Bob到节点2就胜利了
 * 样例 2:
 *
 * 输入:
 * {1,#,2,3,4}
 * 3
 * 4
 * 输出:
 * true
 *
 * 解释:
 *    1
 *     \
 *      2
 *     / \
 *    3  4
 * Alice爬到节点2，胜利
 * 注意事项
 * 保证每个节点对应的val各不相同
 */
public class Lintcode1762 {

    /**
     * @param root: the tree node
     * @param a: the positon of Alice
     * @param b: the position of Bob
     * @return: Can Alice win?
     */
    public boolean getWinner(TreeNode root, int a, int b) {
        //BFS
        // Write your code here
        if(root.val==a) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flagA = false;
        boolean flagB = false;
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if(curr.left!=null) {
                if(curr.left.val==a) {
                    flagA = true;
                }else if(curr.left.val==b) {
                    flagB = true;
                }
                queue.offer(curr.left);
            }
            if(curr.right!=null) {
                if(curr.right.val==a) {
                    flagA = true;
                }else if(curr.right.val==b) {
                    flagB = true;
                }
                queue.offer(curr.right);
            }
            if(flagB&&!flagA) {
                return false;
            }else if(flagA) {
                return true;
            }
        }
        return false;
    }

    public boolean getWinner1(TreeNode root, int a, int b) {
        //DFS
        return dfs(root, a)<=dfs(root, b);
    }

    public int dfs(TreeNode root, int a) {
        if(root==null) {
            return -1;
        }
        if(root.val==a) {
            return 0;
        }
        int left = dfs(root.left, a);
        int right = dfs(root.right, a);
        if(left!=-1) {
            return left+1;
        }else if(right!=-1) {
            return right+1;
        }
        return -1;
    }

}
