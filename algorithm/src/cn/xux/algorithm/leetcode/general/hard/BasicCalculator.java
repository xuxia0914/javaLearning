package cn.xux.algorithm.leetcode.general.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 224. 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 * 输入: "1 + 1"
 * 输出: 2
 *
 * 示例 2:
 * 输入: " 2-1 + 2 "
 * 输出: 3
 *
 * 示例 3:
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 *
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 */
public class BasicCalculator {

    public int calculate(String s) {
        int sign = 1;
        int num = 0;
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int cnt = 0;
        for(char c : s.toCharArray()) {
            if(c=='(') {
                stack.offer(sign);
                cnt += sign==-1?1:0;
                sign = 1;
            }
            if(c==')') {
                ans += sign*(cnt%2==1?-1:1)*num;
                sign = 1;
                num = 0;
                int pre = stack.pollLast();
                if(pre==-1) {
                    cnt--;
                }
            }
            if(c=='-'||c=='+') {
                ans += sign*(cnt%2==1?-1:1)*num;
                sign = c=='+'?1:-1;
                num = 0;
            }
            if(c>='0'&&c<='9') {
                num = num*10+(c-'0');
            }
        }
        return ans+sign*(cnt%2==1?-1:1)*num;
    }

}
