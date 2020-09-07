package cn.xux.algorithm.leetcode.vip.midium;

import java.util.HashSet;
import java.util.Set;

/**
 * 1062-3. 最长重复子串
 * 给定字符串 S，找出最长重复子串的长度。如果不存在重复子串就返回 0。
 *
 * 示例 1：
 * 输入："abcd"
 * 输出：0
 * 解释：没有重复子串。
 *
 * 示例 2：
 * 输入："abbaba"
 * 输出：2
 * 解释：最长的重复子串为 "ab" 和 "ba"，每个出现 2 次。
 *
 * 示例 3：
 * 输入："aabcaabdaab"
 * 输出：3
 * 解释：最长的重复子串为 "aab"，出现 3 次。
 *
 * 示例 4：
 * 输入："aaaaa"
 * 输出：4
 * 解释：最长的重复子串为 "aaaa"，出现 2 次。
 *
 * 提示：
 * 字符串 S 仅包含从 'a' 到 'z' 的小写英文字母。
 * 1 <= S.length <= 1500
 */
public class LongestRepeatingSubstring {

    public int longestRepeatingSubstring(String S) {
        if(S==null||S.length()<2) {
            return 0;
        }
        int n = S.length();
        int res = 0;
        int[][] dp = new int[n+1][n+1];
        for(int i=1;i<=n;i++) {
            for(int j=1;j<i;j++) {
                if(S.charAt(i-1)==S.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

}
