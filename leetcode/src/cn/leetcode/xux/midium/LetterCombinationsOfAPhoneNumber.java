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

    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if(digits==null||digits.length()==0) {
            return res;
        }
        char[][] phoneNum = new char[][]{
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'},
                {'p','q','r','s'},
                {'t','u','v'},
                {'w','x','y','z'}
        };
        if(digits.length()==1) {
            for(char c : phoneNum[digits.charAt(0)-'2']) {
                res.add(c+"");
            }
            return res;
        }
        List<String> postRes = letterCombinations(digits.substring(1));
        int index = digits.charAt(0)-'2';
        for(char c : phoneNum[index]) {
            for(String s : postRes) {
                res.add(c+s);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

}
