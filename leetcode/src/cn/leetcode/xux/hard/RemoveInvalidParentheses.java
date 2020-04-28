package cn.leetcode.xux.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 301. 删除无效的括号
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 *
 * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
 *
 * 示例 1:
 *
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 * 示例 2:
 *
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 * 示例 3:
 *
 * 输入: ")("
 * 输出: [""]
 */
public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        System.out.println(new RemoveInvalidParentheses().removeInvalidParentheses("()())()"));
    }

    public List<String> removeInvalidParentheses(String s) {
        if(s==null||s.length()==0) {
            result.add("");
            return new ArrayList<>(result);
        }
        int len = s.length();
        int leftNum = 0;
        int rightNum = 0;
        int validRight = 0;
        int cnt = 0;
        for(int i=0;i<len;i++) {
            char c = s.charAt(i);
            if(c=='(') {
                leftNum++;
                cnt++;
            }else if(c==')') {
                rightNum++;
                if(cnt>0) {
                    cnt--;
                    validRight++;
                }
            }
        }
        size = 2*validRight;
        dfs(s, "", 0, 0, 0, leftNum, rightNum);
        return new ArrayList<>(result);
    }

    int size = 0;
    Set<String> result = new HashSet<>();
    Set<String> mem = new HashSet<>();

    public void dfs(String s, String curr, int idx, int left, int right, int leftNum, int rightNum) {
        if(left+right==size&&left==right) {
            StringBuilder sb = new StringBuilder();
            sb.append(curr);
            for(int i=idx;i<s.length();i++) {
                if(s.charAt(i)!='('&&s.charAt(i)!=')') {
                    sb.append(s.charAt(i));
                }
            }
            result.add(sb.toString());
            return;
        }
        String key  =curr+"#"+idx;
        if(idx==s.length()||rightNum==0||mem.contains(key)
                ||2*(Math.min(left+leftNum, right+rightNum))<size
                ||left-right>rightNum) {
            return;
        }
        mem.add(key);
        char c = s.charAt(idx);
        if(c=='(') {
            dfs(s, curr+'(', idx+1, left+1, right, leftNum-1, rightNum);
            dfs(s, curr, idx+1, left, right, leftNum-1, rightNum);
        } else if(c==')') {
            if(left>right) {
                dfs(s, curr+')', idx+1, left, right+1, leftNum, rightNum-1);
            }
            dfs(s, curr, idx+1, left, right, leftNum, rightNum-1);
        }else {
            dfs(s, curr+c, idx+1, left, right, leftNum, rightNum);
        }
    }

}
