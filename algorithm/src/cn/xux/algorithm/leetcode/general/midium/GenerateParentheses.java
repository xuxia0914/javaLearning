package cn.xux.algorithm.leetcode.general.midium;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 */
public class GenerateParentheses {

    List<String> list = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        list.clear();
        helper(n, n, "");
        return list;
    }

    private void helper(int left, int right, String curr) {
        if(left==0&&right==0) {
            list.add(curr);
            return;
        }
        if(left==right) {
            helper(left-1, right, curr+'(');
        }
        if(left<right) {
            if(left!=0) {
                helper(left-1, right, curr+'(');
            }
            helper(left, right-1, curr+')');
        }
    }

}
