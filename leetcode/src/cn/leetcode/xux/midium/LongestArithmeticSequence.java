package cn.leetcode.xux.midium;

import java.util.*;

/**
 * 1027. 最长等差数列
 * 给定一个整数数组 A，返回 A 中最长等差子序列的长度。
 * 回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k] 其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。
 * 并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。
 *
 * 示例 1：
 * 输入：[3,6,9,12]
 * 输出：4
 * 解释：
 * 整个数组是公差为 3 的等差数列。
 *
 * 示例 2：
 * 输入：[9,4,7,2,10]
 * 输出：3
 * 解释：
 * 最长的等差子序列是 [4,7,10]。
 *
 * 示例 3：
 * 输入：[20,1,15,3,10,5,8]
 * 输出：4
 * 解释：
 * 最长的等差子序列是 [20,15,10,5]。
 *
 * 提示：
 * 2 <= A.length <= 2000
 * 0 <= A[i] <= 10000
 */
public class LongestArithmeticSequence {

    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        //dp[i][j] 表示到第i个数为止公差为j-10000的最大长度
        int[][] dp = new int[n][20001];
        int result = 0;
        for(int i=1;i<n;i++) {
            for(int j=0;j<i;j++) {
                int cha = A[i]-A[j]+10000;
                dp[i][cha] = Math.max(dp[i][cha], dp[j][cha]+1);
                result = Math.max(result, dp[i][cha]);
            }
        }
        return result+1;
    }

    //TLE
    public int longestArithSeqLength1(int[] A) {
        for(int i=0;i<A.length-1;i++) {
            for(int j=i+1;j<A.length;j++) {
                dfs(A, 2, j+1, A[j]-A[i], j);
            }
        }
        return result;
    }

    int result = 2;

    public void dfs(int[] A, int len, int idx, int target, int pre) {
        if(len+A.length-idx<=result) {
            return;
        }
        if(idx==A.length) {
            result = Math.max(result, len);
        }
        if(A[idx]-pre==target) {
            dfs(A, len+1, idx+1, target, A[idx]);
        }else {
            dfs(A, len, idx+1, target, pre);
        }
    }

}
