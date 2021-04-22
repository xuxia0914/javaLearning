package cn.xux.algorithm.leetcode.general.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 *
 * 示例 2:
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
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
