package cn.leetcode.xux.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 *
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(new LongestValidParentheses().longestValidParentheses(")()()()))()(()())"));
    }

    public int longestValidParentheses(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] dp = new int[len];
        int result = 0;
        for(int i=0;i<len;i++) {
            if(i>0&&chars[i]==')') {
                if(i-1-dp[i-1]>=0&&chars[i-1-dp[i-1]]=='(') {
                    dp[i] = dp[i-1]+2;
                    if(i-2-dp[i-1]>=1) {
                        dp[i] += dp[i-2-dp[i-1]];
                    }
                }
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }

    public int longestValidParentheses1(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        Deque<int[]> deque = new LinkedList<>();
        char[] chars = s.toCharArray();
        int len = chars.length;
        for(int i=0;i<len-1;i++) {
            if(chars[i]=='('&&chars[i+1]==')') {
                if(!deque.isEmpty()&&deque.peekLast()[1]+1==i) {
                    int[] pre = deque.pollLast();
                    deque.offerLast(new int[]{pre[0], i+1});
                }else {
                    deque.offerLast(new int[]{i, i+1});
                }
            }
        }
        boolean notEnd = true;
        int result = 0;
        while(notEnd) {
            notEnd = false;
            int size = deque.size();
            while(size-->0) {
                int[] curr = deque.pollFirst();
                result = Math.max(result, curr[1]-curr[0]+1);
                if(curr[0]>0&&chars[curr[0]-1]=='('
                        &&curr[1]<len-1&&chars[curr[1]+1]==')') {
                    curr[0]--;
                    curr[1]++;
                    notEnd = true;
                }
                if (!deque.isEmpty() && curr[0] == deque.peekLast()[1]+1) {
                    int[] pre = deque.pollLast();
                    deque.offerLast(new int[]{pre[0], curr[1]});
                } else {
                    deque.offerLast(curr);
                }
            }
        }
        return result;
    }

}
