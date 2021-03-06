package cn.xux.algorithm.leetcode.general.easy;

/**
 *给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例 1:
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 */
public class ReverseWordsInAStringIII {

    public static String reverseWords(String s) {
        if(s==null||s.length()<2) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        String[] ss = s.split(" ");
        for(String str : ss) {
            sb.append(new StringBuilder(str).reverse().append(" "));
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

}
