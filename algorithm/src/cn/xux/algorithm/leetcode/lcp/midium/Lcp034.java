package cn.xux.algorithm.leetcode.lcp.midium;

import cn.xux.algorithm.common.TreeNode;

/**
 * LCP 34. 二叉树染色
 * 小扣有一个根结点为 root 的二叉树模型，初始所有结点均为白色，
 * 可以用蓝色染料给模型结点染色，模型的每个结点有一个 val 价值。
 * 小扣出于美观考虑，希望最后二叉树上每个蓝色相连部分的结点个数不能超过 k 个，
 * 求所有染成蓝色的结点价值总和最大是多少？
 *
 * 示例 1：
 * 输入：root = [5,2,3,4], k = 2
 * 输出：12
 * 解释：结点 5、3、4 染成蓝色，获得最大的价值 5+3+4=12
 * image.png
 *
 * 示例 2：
 * 输入：root = [4,1,3,9,null,null,2], k = 2
 * 输出：16
 * 解释：结点 4、3、9 染成蓝色，获得最大的价值 4+3+9=16
 * image.png
 *
 * 提示：
 * 1 <= k <= 10
 * 1 <= val <= 10000
 * 1 <= 结点数量 <= 10000
 */
public class Lcp034 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(new Integer[]{5,2,3,4});
        System.out.println(new Lcp034().maxValue(root, 2));
    }

    public int maxValue(TreeNode root, int k) {
        int[] ans = dfs(root, k);
        return ans[k+1];
    }

    // ans[i](i<=k) 表示包含root节点向下连续的i个节点的最大价值和
    // ans[k+1]表示ans[i](i<=k)的最大值
    private int[] dfs(TreeNode root, int k) {
        int[] ans = new int[k+2];
        if(root==null) {
            return ans;
        }
        int[] la = dfs(root.left, k);
        int[] ra = dfs(root.right, k);
        ans[0] = la[k+1]+ra[k+1];
        int max = ans[0];
        for(int i=1;i<=k;i++) {
            for(int j=0;j<i;j++) {
                ans[i] = Math.max(ans[i], root.val+la[j]+ra[i-1-j]);
            }
            max = Math.max(max, ans[i]);
        }
        ans[k+1] = max;
        return ans;
    }

}
