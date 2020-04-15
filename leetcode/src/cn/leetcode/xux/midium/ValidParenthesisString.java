package cn.leetcode.xux.midium;

import java.util.HashMap;
import java.util.Map;

/**
 * 678. 有效的括号字符串
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。
 * 有效字符串具有如下规则：
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: True
 *
 * 示例 2:
 * 输入: "(*)"
 * 输出: True
 *
 * 示例 3:
 * 输入: "(*))"
 * 输出: True
 *
 * 注意:
 * 字符串大小将在 [1，100] 范围内。
 */
public class ValidParenthesisString {

    public static void main(String[] args) {
        System.out.println(new ValidParenthesisString().checkValidString("()"));
    }

    //贪心
    public boolean checkValidString(String s) {
        if(s==null) {
            return false;
        }
        int len = s.length();
        if(len==0) {
            return true;
        }
        int[][] intervals = new int[len][2];
        char c = s.charAt(0);
        if(c=='(') {
            intervals[0] = new int[]{1,1};
        }else if(c==')') {
            return false;
        }else {
            intervals[0] = new int[]{0,1};
        }
        for(int i=1;i<len;i++) {
            c = s.charAt(i);
            int[] pre = intervals[i-1];
            if(c=='(') {
                intervals[i] = new int[]{pre[0]+1, pre[1]+1};
            }else if(c==')') {
                if(pre[1]==0) {
                    return false;
                }else {
                    intervals[i] = new int[]{Math.max(0, pre[0]-1), pre[1]-1};
                }
            }else {
                intervals[i] = new int[]{Math.max(0, pre[0]-1), pre[1]+1};
            }
        }
        return intervals[len-1][0]==0;
    }

    //递归
    public boolean checkValidString1(String s) {
        if(s==null) {
            return false;
        }
        return checkValidString(s, 0, 0);
    }

    Map<String, Boolean> mem = new HashMap<>();

    public boolean checkValidString(String s, int idx, int cnt) {
        if(idx==s.length()) {
            return cnt==0;
        }
        String key = idx+"#"+cnt;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        boolean result = false;
        char c = s.charAt(idx);
        if(c=='(') {
            result = checkValidString(s, idx+1, cnt+1);
        }else if(c==')') {
            if(cnt==0) {
                result = false;
            }else {
                result = checkValidString(s, idx+1, cnt-1);
            }
        }else {
            result |= checkValidString(s, idx+1, cnt+1);
            result |= checkValidString(s, idx+1, cnt);
            if(cnt>0) {
                result |= checkValidString(s, idx+1, cnt-1);
            }
        }
        mem.put(key, result);
        return result;
    }

}
