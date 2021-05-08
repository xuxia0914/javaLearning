package cn.xux.algorithm.leetcode.general.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 示例 1：
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4：
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5：
 * 输入：s = "mississippi" p = "mis*is*p*."
 * 输出：false
 *
 * 提示：
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    Map<String, Boolean> map = new HashMap<>();

    public boolean isMatch1(String s, String p) {
        if(p.length()==0) {
            return s.length()==0;
        }
        if(p.length()==1) {
            return (s.length()==1)&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.');
        }
        String tmp = s+"#"+p;
        if(map.containsKey(tmp)) {
            return map.get(tmp);
        }
        boolean res;
        if((p.charAt(0)>='a'&&p.charAt(0)<='z')) {
            if(p.charAt(1)=='*') {
                if(s.length()==0) {
                    res = isMatch(s, p.substring(2));
                    map.put(tmp, res);
                    return res;
                }else if(p.charAt(0)==s.charAt(0)) {
                    res = isMatch(s.substring(1), p)||isMatch(s, p.substring(2));
                    map.put(tmp, res);
                    return res;
                }else {
                    res = isMatch(s, p.substring(2));
                    map.put(tmp, res);
                    return res;
                }
            }else {
                if(s.length()==0) {
                    return false;
                }else {
                    res = p.charAt(0)==s.charAt(0)&&isMatch(s.substring(1), p.substring(1));
                    map.put(tmp, res);
                    return res;
                }
            }
        }else if(p.charAt(0)=='.') {
            if(p.charAt(1)=='*') {
                if(s.length()==0) {
                    res = isMatch(s, p.substring(2));
                    map.put(tmp, res);
                    return res;
                }else {
                    res = isMatch(s.substring(1), p)||isMatch(s, p.substring(2));
                    map.put(tmp, res);
                    return res;
                }
            }else {
                if(s.length()==0) {
                    return false;
                }else {
                    res = isMatch(s.substring(1), p.substring(1));
                    map.put(tmp, res);
                    return res;
                }
            }
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        RegularExpressionMatching rem = new RegularExpressionMatching();
        System.out.println(rem.isMatch("", ""));
        System.out.println(rem.isMatch("aa", "a"));
        System.out.println(rem.isMatch("a", "."));
        System.out.println(rem.isMatch("aaa", "aa"));
        System.out.println(rem.isMatch("aa", "a*"));
        System.out.println(rem.isMatch("", "a*"));
        System.out.println(rem.isMatch("", ".*"));
        System.out.println(rem.isMatch("aa", ".*"));
        System.out.println(rem.isMatch("ab", ".*"));
        System.out.println(rem.isMatch("abvb", ".*b"));
        System.out.println(rem.isMatch("aaaaabc", "a*abc"));
        System.out.println(rem.isMatch("aaaaabc", "a*abcd"));
        System.out.println(rem.isMatch("aab", "c*a*b"));
        System.out.println(rem.isMatch("a", ".*..a"));
    }

}
