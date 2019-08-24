package cn.leetcode.xux.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s==null||s.length()<p.length()) {
            return res;
        }
        int lens = s.length();
        int lenp = p.length();
        for(int i=0;i<=lens-lenp;i++) {
            if(isAnagrams(s.substring(i, i+lenp), p)) {
                res.add(i);
            }
        }
        return res;
    }

    public boolean isAnagrams(String s, String t) {
        if(s==null||t==null||s.length()!=t.length()) {
            return false;
        }
        int[] flags = new int[26];
        for(int i=0;i<s.length();i++) {
            flags[s.charAt(i)-'a']++;
            flags[t.charAt(i)-'a']--;
        }
        for(int i : flags) {
            if(i!=0) {
                return false;
            }
        }
        return true;
    }

}
