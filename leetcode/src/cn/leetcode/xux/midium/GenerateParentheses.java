package cn.leetcode.xux.midium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generate Parentheses生成圆括号
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 * “((()))”,
 * “(()())”,
 * “(())()”,
 * “()(())”,
 * “()()()”
 * ]
 * Subscribe to see which companies asked this question
 */
public class GenerateParentheses {

    public static Set<String> solution1(int n) {
        Set<String> result = new HashSet<String>();
        if(n==0) {
            return result;
        }
        if(n==1) {
            result.add("()");
            return result;
        }
        Set<String> postResult = solution1(n-1);
        for(String s : postResult) {
            result.add("()"+s);
            result.add("("+s+")");
            result.add(s+"()");
        }
        return result;
    }

    public static List<String> solution2(int n) {
        List<String> res = new ArrayList<String>();
        helper(n, n, "", res);
        return res;
    }

    public static void helper(int left, int right, String str, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        if (left > 0) {
            helper(left - 1, right, str + '(', res);
        }
        if (right > left) {
            helper(left, right - 1, str + ')', res);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution2(3));
    }

}
