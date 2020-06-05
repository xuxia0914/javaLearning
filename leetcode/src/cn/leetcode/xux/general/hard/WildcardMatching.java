package cn.leetcode.xux.general.hard;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 */
public class WildcardMatching {

    public static boolean solution(String s, String p) {
        if(s.length()==0) {
            if(p.length()==0) {
                return true;
            }else {
                for(int i=0;i<p.length();i++) {
                    if(p.charAt(i)!='*') {
                        return false;
                    }
                }
                return true;
            }
        }else {
            if(p.length()==0) {
                return false;
            }
        }
        if(p.charAt(0)>='a'&&p.charAt(0)<='z') {
            if(p.charAt(0)==s.charAt(0)) {
                return solution(s.substring(1), p.substring(1));
            }else {
                return false;
            }
        }
        if(p.charAt(0)=='?') {
            return solution(s.substring(1), p.substring(1));
        }
        if(p.charAt(0)=='*') {
            return solution(s, p.substring(1))||solution(s.substring(1), p);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution("aa", "a"));
        System.out.println(solution("aa", "aa"));
        System.out.println(solution("aaa", "aa"));
        System.out.println(solution("aa", "*"));
        System.out.println(solution("aa", "a*"));
        System.out.println(solution("ab", "?*"));
        System.out.println(solution("aab", "c*a*b"));
        System.out.println(solution("cb", "?a"));
        System.out.println(solution("adceb", "a*b"));
        System.out.println(solution("acdcb", "a*c?b"));
        System.out.println(solution("acdcb", "*****"));
        System.out.println(solution("acdcb", "???****"));
        System.out.println(solution("acdcb", "a*c????****"));
    }

}
