package cn.leetcode.xux.midium;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LongestPalindromicSubsequence {

    /**
     * 我们建立一个二维的DP数组，其中dp[i][j]表示[i,j]区间内的字符串的最长回文子序列，那么对于递推公式我们分析一下，如果s[i]==s[j]，那么i和j就可以增加2个回文串的长度，我们知道中间dp[i + 1][j - 1]的值，那么其加上2就是dp[i][j]的值。如果s[i] != s[j]，那么我们可以去掉i或j其中的一个字符，然后比较两种情况下所剩的字符串谁dp值大，就赋给dp[i][j]，那么递推公式如下：
     *               /  dp[i + 1][j - 1] + 2                       if (s[i] == s[j])
     *
     * dp[i][j] =
     *
     *               \  max(dp[i + 1][j], dp[i][j - 1])        if (s[i] != s[j])
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        if(s==null) {
            return 0;
        }
        char[] ss = s.toCharArray();
        int n = ss.length;
        if(n<2) {
            return n;
        }
        int[][] dp = new int[n][n];
        for(int i=n-1;i>=0;i--) {
            dp[i][i] = 1;
            for(int j=i+1;j<n;j++) {
                if(ss[i]==ss[j]) {
                    dp[i][j] = dp[i+1][j-1]+2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public String longestPalindrome1(String s) {
        if(s==null||s.length()<2) {
            return s;
        }
        int n = s.length();
        String result = "";
        for(int i=0;i<n;i++) {
            int left = i;
            int right = i;
            while(left>=0&&right<n&&s.charAt(left)==s.charAt(right)) {
                left--;
                right++;
            }
            if(right-left-1>result.length()) {
                result = s.substring(left+1, right);
            }
        }
        for(int i=0;i<n;i++) {
            int left = i;
            int right = i+1;
            while(left>=0&&right<n&&s.charAt(left)==s.charAt(right)) {
                left--;
                right++;
            }
            if(right-left-1>result.length()) {
                result = s.substring(left+1, right);
            }
        }
        return result;
    }

}
