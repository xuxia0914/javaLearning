package cn.leetcode.xux.common;

import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
//        System.out.println(new Test1().getAns(new int[]{8,9,7,3,0,5,11}));
    }

    /**
     * 193. 最长有效括号
     * 中文English
     * 给出一个只包含'(' 和')'的字符串，找出其中最长的左右括号正确匹配的合法子串。
     *
     * 样例
     * 样例 1:
     *
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 样例 2:
     *
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     */
    public int longestValidParentheses(String s) {
        // write your code here
        if(s==null||s.length()<2) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int cnt = 0;
        int ans = 0;
        for(int i=0;i<s.length();i++) {
            cnt += s.charAt(i)=='('?1:-1;
            if(cnt<0) {
                map.clear();
                cnt  =0;
                map.put(0, i);
            }else if(map.containsKey(cnt)) {
                ans = Math.max(ans, i-map.get(cnt));
            }else {
                map.put(cnt, i);
            }
        }
        return ans;
    }

}