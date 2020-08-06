package cn.xux.algorithm.leetcode.general.hard;

/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 *
 * 示例 3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *
 * 示例 4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 *
 * 示例 5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        int lenS = s.length();
        int lenP = p.length();
        boolean[] dp = new boolean[lenS+1];
        dp[0] = true;
        for(int i=0;i<lenP;i++) {
            char c = p.charAt(i);
            boolean[] newDp = new boolean[lenS+1];
            if (c == '?') {
                for(int j=0;j<lenS;j++) {
                    newDp[j + 1] = dp[j];
                }
            } else if (c == '*') {
                newDp[0] = dp[0];
                for(int j=1;j<=lenS;j++) {
                    newDp[j] = newDp[j-1]|dp[j];
                }
            } else {
                for(int j=0;j<lenS;j++) {
                    if (s.charAt(j) == c) {
                        newDp[j + 1] = dp[j];
                    }
                }
            }
            dp = newDp;
        }
        return dp[lenS];
    }

    public static void main(String[] args) {
        WildcardMatching wm = new WildcardMatching();
//        System.out.println(wm.isMatch("aa", "a"));
//        System.out.println(wm.isMatch("aa", "aa"));
//        System.out.println(wm.isMatch("aaa", "aa"));
//        System.out.println(wm.isMatch("aa", "*"));
        System.out.println(wm.isMatch("a", "a*"));
//        System.out.println(wm.isMatch("ab", "?*"));
//        System.out.println(wm.isMatch("aab", "c*a*b"));
//        System.out.println(wm.isMatch("cb", "?a"));
//        System.out.println(wm.isMatch("adceb", "a*b"));
//        System.out.println(wm.isMatch("acdcb", "a*c?b"));
//        System.out.println(wm.isMatch("acdcb", "*****"));
//        System.out.println(wm.isMatch("acdcb", "???****"));
//        System.out.println(wm.isMatch("acdcb", "a*c????****"));
//        System.out.println(wm.isMatch("adceb", "*a*b"));
//        System.out.println(wm.isMatch("mississippi", "m??*ss*?i*pi"));
    }

}
