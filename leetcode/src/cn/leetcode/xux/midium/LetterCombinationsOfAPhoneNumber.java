package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsOfAPhoneNumber {

    String[] dict = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> list = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        list.clear();
        if(digits==null||digits.length()==0) {
            return list;
        }
        helper(0, "", digits);
        return list;
    }

    public void helper(int i, String curr, String digits) {
        if(i==digits.length()) {
            list.add(curr);
            return;
        }
        String s = dict[digits.charAt(i)-'2'];
        for(char c : s.toCharArray()) {
            helper(i+1, curr+c, digits);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber lc = new LetterCombinationsOfAPhoneNumber();
        System.out.println(lc.letterCombinations("23"));
    }

}
