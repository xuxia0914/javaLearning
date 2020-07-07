package cn.leetcode.xux.common;

import javafx.util.Pair;

import java.util.*;

public class Test1 {

    public static void main(String[] args) {
        //System.out.println(new Test1().thirdMax(new int[]{1,2,2,5,3,5}));
    }

    /**
     * 927. 翻转字符串II
     * 中文English
     * 给定输入的字符数组，逐词翻转数组。单词被定义为不包含空格的字符串.
     * 输入字符数组不包含前导或尾部空格，单词总是用单个空格分隔。
     *
     * 样例
     * 样例1
     *
     * 输入: s = "the sky is blue"
     * 输出: "blue is sky the"
     * 样例2
     *
     * 输入: "a b c"
     * 输出: "c b a"
     * 挑战
     * 你能在不分配额外空间的情况下原地解决这个问题吗？
     */
    public char[] reverseWords(char[] str) {
        // write your code here
        String s = new String(str);
        String[] words = s.split(" ");
        for(int i=0;i<words.length/2;i++) {
            String tmp = words[i];
            words[i] = words[words.length-1-i];
            words[words.length-1-i] = tmp;
        }
        String ans = String.join(" ", words);
        return ans.toCharArray();
    }

}
