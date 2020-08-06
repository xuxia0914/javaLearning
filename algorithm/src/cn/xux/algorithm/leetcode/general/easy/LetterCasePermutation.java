package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串中字母的大小写组合
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 * Input: S = "12345"
 * Output: ["12345"]
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<String>();
        if(S==null||S.length()==0) {
            return result;
        }
        helper(result, "", S);
        return result;
    }

    public void helper(List<String> res, String curr, String S) {
        if(curr.length()==S.length()) {
            res.add(curr);
            return;
        }
        char c = S.charAt(curr.length());
        helper(res, curr+c, S);
        if(c>='a'&&c<='z') {
            char tmp = (char)(c - ('a'-'A'));
            helper(res, curr+tmp, S);
        }else if(c>='A'&&c<='Z') {
            char tmp = (char)(c + ('a'-'A'));
            helper(res, curr+tmp, S);
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation lcp = new LetterCasePermutation();
        System.out.println(lcp.letterCasePermutation("a1b2"));
    }

}
