package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1358. 包含所有三种字符的子字符串数目
 * 给你一个字符串 s ，它只包含三种字符 a, b 和 c 。
 * 请你返回 a，b 和 c 都 至少 出现过一次的子字符串数目。
 *
 * 示例 1：
 * 输入：s = "abcabc"
 * 输出：10
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" 和 "abc" (相同字符串算多次)。
 *
 * 示例 2：
 * 输入：s = "aaacb"
 * 输出：3
 * 解释：包含 a，b 和 c 各至少一次的子字符串为 "aaacb", "aacb" 和 "acb" 。
 *
 * 示例 3：
 * 输入：s = "abc"
 * 输出：1
 *
 * 提示：
 * 3 <= s.length <= 5 x 10^4
 * s 只包含字符 a，b 和 c 。
 */
public class NumberOfSubstringsContainingAllThreeCharacters {

    public int numberOfSubstrings(String s) {
        int lastA = -1;
        int lastB = -1;
        int lastC = -1;
        int len = s.length();
        int ans = 0;
        for(int i=0;i<len;i++) {
            char c = s.charAt(i);
            if(c=='a') {
                int left = Math.min(lastB, lastC);
                ans += left+1;
                lastA = i;
            }else if(c=='b') {
                int left = Math.min(lastA, lastC);
                ans += left+1;
                lastB = i;
            }else {
                int left = Math.min(lastA, lastB);
                ans += left+1;
                lastC = i;
            }
        }
        return ans;
    }

}
