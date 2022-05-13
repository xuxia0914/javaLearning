package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1784. 检查二进制字符串字段
 * 给你一个二进制字符串 s ，该字符串 不含前导零 。
 * 如果 s 最多包含 一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：s = "1001"
 * 输出：false
 * 解释：字符串中的 1 没有形成一个连续字段。
 * <p>
 * 示例 2：
 * 输入：s = "110"
 * 输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 100
 * s[i]​​​​ 为 '0' 或 '1'
 * s[0] 为 '1'
 */
public class CheckIfBinaryStringHasAtMostOneSegmentOfOnes {

    public boolean checkOnesSegment(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0' && s.charAt(i + 1) == '1') {
                return false;
            }
        }
        return true;
    }

    public boolean checkOnesSegment1(String s) {
        boolean flag = false;
        for (int i = 0; i < s.length() - 1; i++) {
            if (flag && s.charAt(i) == '1') {
                return false;
            }
            if (s.charAt(i) == '1' && s.charAt(i + 1) == '0') {
                flag = true;
            }
        }
        return !flag || s.charAt(s.length() - 1) == '0';
    }

}
