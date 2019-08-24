package cn.leetcode.xux.easy;

import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * Example 1:
 * Input: "()"
 * Output: true
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * Input: "(]"
 * Output: false
 * Example 4:
 * Input: "([)]"
 * Output: false
 * Example 5:
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {

    public static boolean solution(String s) {
        if(s==null) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char curr, top;
        for(int i=0;i<s.length();i++) {
            curr = s.charAt(i);
            if(curr=='('||curr=='['||curr=='{') {
                stack.push(curr);
            }
            if(curr==')'||curr==']'||curr=='}') {
                if(stack.isEmpty()) {
                    return false;
                }
                top = stack.pop();
                if(curr==')'&&top!='(') {
                    return false;
                }
                if(curr==']'&&top!='[') {
                    return false;
                }
                if(curr=='}'&&top!='{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(solution("()"));
        System.out.println(solution("()[]{}"));
        System.out.println(solution("(]"));
        System.out.println(solution("([)]"));
        System.out.println(solution("{[]}"));
    }

}
