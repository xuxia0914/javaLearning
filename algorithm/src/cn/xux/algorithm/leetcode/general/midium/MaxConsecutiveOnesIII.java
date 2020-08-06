package cn.xux.algorithm.leetcode.general.midium;

import java.util.Arrays;

/**
 * 1004. 最大连续1的个数 III
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 * 示例 1：
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *
 * 示例 2：
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *
 * 提示：
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1
 */
public class MaxConsecutiveOnesIII {

    public int longestOnes(int[] A, int K) {
        int res = 0;
        int left=0,right=0;
        for(;right<A.length;right++) {
            if(A[right]==0) {
                if(K==0) {
                    while(A[left]==1) {
                        left++;
                    }
                    left++;
                }else {
                    K--;
                }
            }
            res = Math.max(res, right-left+1);
        }
        return res;
    }

    public int longestOnes1(int[] A, int K) {
        int[] dp = new int[K+1];
        Arrays.fill(dp, 1);
        dp[0] = A[0];
        int res = 0;
        for(int i=1;i<A.length;i++) {
            int num = A[i];
            if(num==1) {
                for(int j=0;j<=K;j++) {
                    dp[j]++;
                }
            }else {
                res = Math.max(res, dp[K]);
                for(int j=K;j>0;j--) {
                    dp[j] = dp[j-1]+1;
                }
                dp[0] = 0;
            }
        }
        res = Math.max(res, dp[K]);
        return res;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesIII mco = new MaxConsecutiveOnesIII();
        System.out.println(mco.longestOnes(new int[]{0,0,0,1}, 4));
    }

}
