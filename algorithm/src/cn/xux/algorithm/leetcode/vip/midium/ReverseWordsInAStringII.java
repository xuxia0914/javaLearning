package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 186. 翻转字符串里的单词 II
 *
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例：
 *
 * 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 注意：
 *
 * 单词的定义是不包含空格的一系列字符
 * 输入字符串中不会包含前置或尾随的空格
 * 单词与单词之间永远是以单个空格隔开的
 * 进阶：使用 O(1) 额外空间复杂度的原地解法。
 */
public class ReverseWordsInAStringII {

    public void reverseWords(char[] s) {
        if(s==null||s.length<2) {
            return;
        }
        int n = s.length;
        reverse(s, 0, n-1);
        int start = 0;
        while(start<n) {
            int end = start;
            while(end<n-1&&s[end+1]!=' ') {
                end++;
            }
            reverse(s, start, end);
        }
    }

    private void reverse(char[] s, int start, int end) {
        while(start<end) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
            start++;
            end--;
        }
    }

}
