package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1869. 哪种连续子字符串更长
 * 给你一个二进制字符串 s 。
 * 如果字符串中由 1 组成的 最长 连续子字符串
 * 严格长于 由 0 组成的 最长 连续子字符串，返回 true ；
 * 否则，返回 false 。
 * 例如，s = "110100010" 中，由 1 组成的最长连续子字符串的长度是 2 ，
 * 由 0 组成的最长连续子字符串的长度是 3 。
 * 注意，如果字符串中不存在 0 ，此时认为由 0 组成的最长连续子字符串的长度是 0 。
 * 字符串中不存在 1 的情况也适用此规则。
 *
 * 示例 1：
 * 输入：s = "1101"
 * 输出：true
 * 解释：
 * 由 1 组成的最长连续子字符串的长度是 2："1101"
 * 由 0 组成的最长连续子字符串的长度是 1："1101"
 * 由 1 组成的子字符串更长，故返回 true 。
 *
 * 示例 2：
 * 输入：s = "111000"
 * 输出：false
 * 解释：
 * 由 1 组成的最长连续子字符串的长度是 3："111000"
 * 由 0 组成的最长连续子字符串的长度是 3："111000"
 * 由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
 *
 * 示例 3：
 * 输入：s = "110100010"
 * 输出：false
 * 解释：
 * 由 1 组成的最长连续子字符串的长度是 2："110100010"
 * 由 0 组成的最长连续子字符串的长度是 3："110100010"
 * 由 1 组成的子字符串不比由 0 组成的子字符串长，故返回 false 。
 *
 * 提示：
 * 1 <= s.length <= 100
 * s[i] 不是 '0' 就是 '1'
 */
public class LongerContiguousSegmentsOfOnesThanZeros {

    public boolean checkZeroOnes(String s) {
        int len0 = 0;
        int len1 = 0;
        int cnt0 = 0;
        int cnt1 = 0;
        for(char c : s.toCharArray()) {
            if(c=='0') {
                cnt0++;
                len0 = Math.max(len0, cnt0);
                cnt1 = 0;
            }else {
                cnt1++;
                len1 = Math.max(len1, cnt1);
                cnt0 = 0;
            }
        }
        return len1>len0;
    }

}
