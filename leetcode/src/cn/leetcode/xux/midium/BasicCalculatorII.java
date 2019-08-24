package cn.leetcode.xux.midium;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 * You may assume that the given expression is always valid.
 * Some examples:
 * "3+2*2" = 7
 * " 3/2 " = 1
 * " 3+5 / 2 " = 5
 * Note: Do not use the eval built-in library function.
 */
public class BasicCalculatorII {

    public static int solution(String s) {
        if(s==null||"".equals(s)||"".equals(s.trim())) {}
        int result = 0;
        s = s.replaceAll(" ", "");
        Stack<String> stack = new Stack<String>();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)>='0'&&s.charAt(i)<='9'||s.charAt(i)=='-') {
                int j;
                for(j=i+1;j<s.length();j++) {
                    if(s.charAt(j)<'0'||s.charAt(j)>'9') {
                        break;
                    }
                }
                stack.push(s.substring(i, j));
                i=j-1;
            }else if(s.charAt(i)=='*'||s.charAt(i)=='/') {
                String x1="", x2="";
                int j;
                for(j=i+1;j<s.length();j++) {
                    if(s.charAt(j)<'0'||s.charAt(j)>'9') {
                        break;
                    }
                }
                x1 = s.substring(i+1, j);
                x2 = stack.pop();
                if(s.charAt(i)=='*') {
                    stack.push((String.valueOf(Integer.valueOf(x1)*Integer.valueOf(x2))));
                }else {
                    stack.push((String.valueOf(Integer.valueOf(x2)/Integer.valueOf(x1))));
                }
                i=j-1;
            }
        }
        while(!stack.isEmpty()) {
            result += Integer.valueOf(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("3+2*2"));
        System.out.println(solution(" 3/2 "));
        System.out.println(solution(" 3+5 / 2 "));
        System.out.println(solution(" 3-5 / 2 "));
    }

}
