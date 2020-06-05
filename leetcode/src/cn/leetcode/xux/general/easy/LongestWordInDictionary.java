package cn.leetcode.xux.general.easy;

import java.util.*;

public class LongestWordInDictionary {

    public String longestWord(String[] words) {
        if(words==null||words.length==0) {
            return "";
        }
        Arrays.sort(words);
        Set<String> set = new HashSet<String>();
        set.add("");
        String res = "";
        for(String s : words) {
            if(set.contains(s.substring(0, s.length()-1))) {
                set.add(s);
                if(s.length()>res.length()) {
                    res = s;
                }
            }
        }
        return res;
    }

}
