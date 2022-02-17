package cn.xux.algorithm.leetcode.general.hard;

/**
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 * <p>
 * 返回符合要求的 最少分割次数 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：s = "ab"
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 */
public class PalindromePartitioningII {

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioningII().minCut("aab"));
    }

    public int minCut(String s) {
        int len = s.length();
        // flag[i][j]=true 表示子串s[i~j]是回文串
        boolean[][] flag = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            flag[i][i] = true;
            int r = 1;
            while (i - r >= 0 && i + r < len && s.charAt(i - r) == s.charAt(i + r)) {
                flag[i - r][i + r] = true;
                r++;
            }
        }
        for (int i = 0; i < len - 1; i++) {
            int r = 1;
            while (i - r + 1 >= 0 && i + r < len && s.charAt(i - r + 1) == s.charAt(i + r)) {
                flag[i - r + 1][i + r] = true;
                r++;
            }
        }
        // dp[i]表示子串s[0~i]需要分割成回文串需要的最少分割次数
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            if (flag[0][i]) {
                continue;
            }
            dp[i] = i;
            for (int j = 0; j < i; j++) {
                if (flag[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len - 1];
    }

}
