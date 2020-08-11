package cn.xux.algorithm.leetcode.vip.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 291. Word Pattern II
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection
 * between a letter in pattern and a non-empty substring in str.
 *
 * Example 1:
 * Input: pattern = "abab", str = "redblueredblue"
 * Output: true
 *
 * Example 2:
 * Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
 * Output: true
 *
 * Example 3:
 * Input: pattern = "aabb", str = "xyzabcxzyabc"
 * Output: false
 *
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 */
public class WordPatternII {

    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        if(pattern==null||str==null||pattern.length()>str.length()) {
            return false;
        }
        return dfs(pattern, 0, str, 0);
    }

    Map<String, Integer> map = new HashMap<>();
    String[] mapping = new String[26];

    private boolean dfs(String pattern, int pi, String str, int si) {
        if(pi==pattern.length()&&si==str.length()) {
            return true;
        }
        if(pi>=pattern.length()||si>=str.length()) {
            return false;
        }
        int curr = pattern.charAt(pi)-'a';
        if(mapping[curr]!=null) {
            if(si+mapping[curr].length()>str.length()
                    ||!str.startsWith(mapping[curr], si)) {
                return false;
            }
            return dfs(pattern, pi+1, str, si+mapping[curr].length());
        }else {
            for(int i=si+1;i<=str.length();i++) {
                String s = str.substring(si, i);
                if(!map.containsKey(s)) {
                    mapping[curr] = s;
                    map.put(s, curr);
                    if(dfs(pattern, pi+1, str, i)) {
                        return true;
                    }
                    map.remove(s);
                    mapping[curr] = null;
                }

            }
        }
        return false;
    }

}
