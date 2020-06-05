package cn.leetcode.xux.general.midium;

import java.util.Stack;

/**
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 * 注意，您的结果中 不应 包含任何括号。
 *
 * 示例 1：
 * 输入：s = "(abcd)"
 * 输出："dcba"
 *
 * 示例 2：
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 *
 * 示例 3：
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 *
 * 示例 4：
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 *
 * 提示：
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {

    public static void main(String[] args) {
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().reverseParentheses("(abcd)"));
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().reverseParentheses("vdgzyj()"));
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().reverseParentheses("(u(love)i)"));
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().reverseParentheses("(ed(et(oc))el)"));
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().reverseParentheses("a(bcdefghijkl(mno)p)q"));
        System.out.println(new ReverseSubstringsBetweenEachPairOfParentheses().reverseParentheses("ta()usw((((a))))"));
    }

    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(c=='(') {
                stack.push(curr.toString());
                curr = new StringBuilder();
            }else if(c==')') {
                curr = new StringBuilder().append(stack.pop()).append(curr.reverse());
            }else {
                curr.append(c);
            }
        }
        return curr.toString();
    }

}
