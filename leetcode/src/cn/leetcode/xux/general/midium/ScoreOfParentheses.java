package cn.leetcode.xux.general.midium;

import java.util.Stack;

/**
 * 856. 括号的分数
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *
 * 示例 1：
 * 输入： "()"
 * 输出： 1
 *
 * 示例 2：
 * 输入： "(())"
 * 输出： 2
 *
 * 示例 3：
 * 输入： "()()"
 * 输出： 2
 *
 * 示例 4：
 * 输入： "(()(()))"
 * 输出： 6
 *
 * 提示：
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {

    public int scoreOfParentheses(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') {
                bal++;
            } else {
                bal--;
                if (S.charAt(i-1) == '(')
                    ans += 1 << bal;
            }
        }
        return ans;
    }

    public int scoreOfParentheses1(String S) {
        if(S==null||S.length()==0) {
            return 0;
        }
        Stack<String> stack = new Stack<>();
        for(int i=0;i<S.length();i++) {
            char c = S.charAt(i);
            if(c=='(') {
                stack.push("(");
            }else {
                int sum = 0;
                while(!stack.peek().equals("(")) {
                    sum += Integer.parseInt(stack.pop());
                }
                stack.pop();
                sum = sum==0?1:2*sum;
                stack.push(String.valueOf(sum));
            }
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += Integer.parseInt(stack.pop());
        }
        return result;
    }

}
