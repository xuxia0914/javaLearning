package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
 * Example:
 * Input: ["Hello", "Alaska", "Dad", "Peace"]
 * Output: ["Alaska", "Dad"]
 * Note:
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {

    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        String[] strs = new String[]{"qwertyuiopQWERTYUIOP", "asdfghjklASDFGHJKL", "zxcvbnmZXCVBNM"};
        if(words==null||words.length==0) {
            return null;
        }
        String tmp = "";
        for(String s : words) {
            if(s==null) {
                continue;
            }
            if(s.length()==0) {
                res.add(s);
            }
            for(String str : strs) {
                if(str.contains(s.substring(0, 1))) {
                    tmp = str;
                }
            }
            boolean flag = true;
            for(int i=1;i<s.length();i++) {
                if(!tmp.contains(s.substring(i, i+1))) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                res.add(s);
            }
        }
        return res.toArray(new String[res.size()]);
    }

}
