package cn.xux.algorithm.leetcode.general.hard;

/**
 * 664. 奇怪的打印机
 * 有台奇怪的打印机有以下两个特殊要求：
 * <p>
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在从起始到结束的任意位置打印新字符(新打印的字符位置要连续)，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaabbb"
 * 输出：2
 * 解释：首先打印 "aaa" 然后打印 "bbb"。
 * 示例 2：
 * <p>
 * 输入：s = "aba"
 * 输出：2
 * 解释：首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 100
 * s 由小写英文字母组成
 */
public class StrangePrinter {

    public static void main(String[] args) {
        System.out.println(new StrangePrinter().strangePrinter(
                "baacdddaaddaaaaccbddbcabdaabdbbcdcbbbacbddcabcaaa" // 19
        ));
    }

    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = len;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

}
