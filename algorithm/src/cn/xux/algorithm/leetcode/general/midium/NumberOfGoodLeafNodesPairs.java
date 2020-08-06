package cn.xux.algorithm.leetcode.general.midium;

import cn.xux.algorithm.common.TreeNode;

import java.math.BigInteger;

/**
 * 1530. 好叶子节点对的数量
 * 给你二叉树的根节点 root 和一个整数 distance 。
 * 如果二叉树中两个 叶 节点之间的 最短路径长度 小于或者等于 distance ，
 * 那它们就可以构成一组 好叶子节点对 。
 * 返回树中 好叶子节点对的数量 。
 *
 * 示例 1：
 * 输入：root = [1,2,3,null,4], distance = 3
 * 输出：1
 * 解释：树的叶节点是 3 和 4 ，它们之间的最短路径的长度是 3 。这是唯一的好叶子节点对。
 *
 * 示例 2：
 * 输入：root = [1,2,3,4,5,6,7], distance = 3
 * 输出：2
 * 解释：好叶子节点对为 [4,5] 和 [6,7] ，最短路径长度都是 2 。
 * 但是叶子节点对 [4,6] 不满足要求，因为它们之间的最短路径长度为 4 。
 *
 * 示例 3：
 * 输入：root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
 * 输出：1
 * 解释：唯一的好叶子节点对是 [2,5] 。
 *
 * 示例 4：
 * 输入：root = [100], distance = 1
 * 输出：0
 *
 * 示例 5：
 * 输入：root = [1,1,1], distance = 2
 * 输出：1
 *
 * 提示：
 * tree 的节点数在 [1, 2^10] 范围内。
 * 每个节点的值都在 [1, 100] 之间。
 * 1 <= distance <= 10
 */
public class NumberOfGoodLeafNodesPairs {

    public static void main(String[] args) {
        BigInteger bi1 = new BigInteger("999999999999999999999999999999", 10);
        String s1 = bi1.toString(10);
        String s2 = bi1.toString(16);
        BigInteger bi2 = new BigInteger(s2, 16);
        String s3 = bi2.toString(10);
        String s4 = bi2.toString(16);
        System.out.println(s1 + ",len=" + s1.length());
        System.out.println(s2 + ",len=" + s2.length());
        System.out.println(s3 + ",len=" + s3.length());
        System.out.println(s4 + ",len=" + s4.length());
    }


    public int countPairs(TreeNode root, int distance) {
        dfs(root, distance);
        return ans;
    }

    int ans = 0;

    public int[] dfs(TreeNode node, int dis) {
        int[] currAns = new int[dis];
        if(node==null) {
            return currAns;
        }
        int[] leftAns = dfs(node.left, dis);
        int[] rightAns = dfs(node.right, dis);
        for(int i=0;i<dis;i++) {
            for(int j=0;i+j+2<=dis;j++) {
                ans += leftAns[i]*rightAns[j];
            }
        }
        if(node.left==null&&node.right==null) {
            currAns[0] = 1;
        }else {
            for(int i=0;i<dis-1;i++) {
                currAns[i+1] += leftAns[i]+rightAns[i];
            }
        }
        return currAns;
    }

}
