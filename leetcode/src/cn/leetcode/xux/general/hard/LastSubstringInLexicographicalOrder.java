package cn.leetcode.xux.general.hard;

import java.util.Stack;

/**
 * 5069. 按字典序排在最后的子串
 * 给你一个字符串 s，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
 *
 * 示例 1：
 * 输入："abab"
 * 输出："bab"
 * 解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"。
 *
 * 示例 2：
 * 输入："leetcode"
 * 输出："tcode"
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * s 仅含有小写英文字符。
 */
public class LastSubstringInLexicographicalOrder {

    public String lastSubstring(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack  = new Stack<>();
        int pre = -2;
        for(int i=0;i<s.length();i++) {
            if(stack.isEmpty()) {
                stack.push(i);
                pre = i;
            }else if(s.charAt(stack.peek())==s.charAt(i)) {
                if(i-1>pre) {
                    stack.push(i);
                }
                pre = i;
            }else if(s.charAt(stack.peek())<s.charAt(i)) {
                stack.clear();
                stack.push(i);
            }
        }
        while(!stack.isEmpty()) {
            int size = stack.size();
            sb.append(s.charAt(stack.peek()));

            Stack<Integer> tmp  = new Stack<>();
            for(int i=0;i<size;i++) {
                int curr = stack.pop();
                if(curr<s.length()-1) {
                    if(tmp.isEmpty()) {
                        tmp.push(curr+1);
                    }else if(s.charAt(tmp.peek())==s.charAt(curr+1)) {
                        if(Math.abs(curr+1-pre)>1) {
                            tmp.push(curr+1);
                        }
                        pre = curr+1;
                    }else if(s.charAt(tmp.peek())<s.charAt(curr+1)) {
                        tmp.clear();
                        tmp.push(curr+1);
                    }
                }
            }
            stack.addAll(tmp);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new LastSubstringInLexicographicalOrder().lastSubstring("abab");
    }

}
