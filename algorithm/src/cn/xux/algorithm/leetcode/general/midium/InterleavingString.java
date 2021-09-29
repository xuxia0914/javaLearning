package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 *
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return f[n][m];
    }

    public boolean isInterleave1(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()) {
            return false;
        }
        if(s1.length()==0) {
            return s2.equals(s3);
        }
        if(s2.length()==0) {
            return s1.equals(s3);
        }
        return helper(s1, 0, s2, 0, s3, 0);
    }

    Map<String, Boolean> mem = new HashMap<>();

    public boolean helper(String s1, int idx1, String s2, int idx2, String s3, int idx3) {
        if(idx3==s3.length()) {
            return true;
        }
        String key = idx1+"#"+idx2;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        if(idx1<s1.length()&&s1.charAt(idx1)==s3.charAt(idx3)
                &&helper(s1, idx1+1, s2, idx2, s3, idx3+1)) {
            mem.put(key, true);
            return true;
        }
        if(idx2<s2.length()&&s2.charAt(idx2)==s3.charAt(idx3)
                &&helper(s1, idx1, s2, idx2+1, s3, idx3+1)) {
            mem.put(key, true);
            return true;
        }
        mem.put(key, false);
        return false;
    }

}
