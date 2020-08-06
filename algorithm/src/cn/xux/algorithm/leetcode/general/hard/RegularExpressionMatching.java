package cn.xux.algorithm.leetcode.general.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */
public class RegularExpressionMatching {

    Map<String, Boolean> map = new HashMap<>();

    public boolean isMatch(String s, String p) {
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
