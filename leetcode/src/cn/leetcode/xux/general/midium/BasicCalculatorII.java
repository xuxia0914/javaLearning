package cn.leetcode.xux.general.midium;

import java.util.Stack;

/**
 * 227. 基本计算器 II
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class BasicCalculatorII {

    public int calculate(String s) {
        if(s==null||"".equals(s)||"".equals(s.trim())) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int len = s.length();
        int num = 0;
        char pre = '+';
        char c;
        for(int i=0;i<len;i++) {
            c = s.charAt(i);
            if(c==' ') {
                continue;
            }else if(c=='+'||c=='-'||c=='*'||c=='/') {
                if(pre=='*') {
                    stack.push(stack.pop()*num);
                }else if(pre=='/') {
                    stack.push(stack.pop()/num);
                }else if(pre=='-') {
                    stack.push(-num);
                }else if(pre=='+') {
                    stack.push(num);
                }
                pre = c;
                num = 0;
            }else {
                num = num*10+c-'0';
            }
        }
        if(pre=='*') {
            stack.push(stack.pop()*num);
        }else if(pre=='/') {
            stack.push(stack.pop()/num);
        }else if(pre=='-') {
            stack.push(-num);
        }else {
            stack.push(num);
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        BasicCalculatorII calc = new BasicCalculatorII();
//        System.out.println(calc.calculate("3+2*2"));
//        System.out.println(calc.calculate(" 3/2 "));
//        System.out.println(calc.calculate(" 3+5 / 2 "));
        System.out.println(calc.calculate(" 3-5 / 2 "));
    }

}
