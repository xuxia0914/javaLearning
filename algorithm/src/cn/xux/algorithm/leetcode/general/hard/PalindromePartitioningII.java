package cn.xux.algorithm.leetcode.general.hard;

import java.util.Arrays;

/**
 * 132. 分割回文串 II
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 */
public class PalindromePartitioningII {

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioningII().minCut("aab"));
    }

    public int minCut(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for(int[] arr : dp) {
            Arrays.fill(arr, len);
        }
        for(int i=0;i<len;i++) {
            int r = 1;
            while(i-r+1>=0&&i+r-1<len&&s.charAt(i-r+1)==s.charAt(i+r-1)) {
                dp[i-r+1][i+r-1] = 1;
                r++;
            }
        }
        for(int i=0;i<len-1;i++) {
            int r = 1;
            while(i-r+1>=0&&i+r<len&&s.charAt(i-r+1)==s.charAt(i+r)) {
                dp[i-r+1][i+r] = 1;
                r++;
            }
        }
        for(int i=2;i<=len;i++) {
            for(int start=0;start+i-1<len;start++) {
                if(dp[start][start+i-1]==1) {
                    break;
                }
                for(int preEnd=start;preEnd<start+i-1;preEnd++) {
                    dp[start][start+i-1] = Math.min(dp[start][start+i-1], dp[start][preEnd]+dp[preEnd+1][start+i-1]);
                }
            }
        }
        return dp[0][len-1];
    }

}
