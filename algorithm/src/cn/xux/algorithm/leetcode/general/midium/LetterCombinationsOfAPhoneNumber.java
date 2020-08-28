package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
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
