package cn.xux.algorithm.leetcode.general.midium;

/**
 * 516. 最长回文子序列
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。
 * 可以假设 s 的最大长度为 1000 。
 *
 * 示例 1:
 * 输入: "bbbab"
 * 输出: 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入: "cbbd"
 * 输出: 2
 * 一个可能的最长回文子序列为 "bb"。
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 只包含小写英文字母
 */
public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        System.out.println(new LongestPalindromicSubsequence().longestPalindromeSubseq("abcde"));
    }

    public int longestPalindromeSubseq(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++) {
            dp[i][i] = 1;
        }
        for(int len=2;len<=n;len++) {
            for(int start=0;start+len-1<n;start++) {
                int end = start+len-1;
                if(s.charAt(start)==s.charAt(end)) {
                    dp[start][end] = 2+dp[start+1][end-1];
                }else {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }
        return dp[0][n-1];
    }

}
