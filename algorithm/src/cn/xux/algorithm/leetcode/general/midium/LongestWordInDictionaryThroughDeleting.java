package cn.xux.algorithm.leetcode.general.midium;

import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，
 * 找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 *
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 *
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 */
public class LongestWordInDictionaryThroughDeleting {

    public String findLongestWord(String s, List<String> dictionary) {
        String res = "";
        if (s == null || s.length() == 0
                || dictionary == null || dictionary.size() == 0) {
            return res;
        }
        int index1, index2;
        for (String s1 : dictionary) {
            index1 = 0;
            index2 = 0;
            while (index1 < s.length() && index2 < s1.length()) {
                if (s.charAt(index1) == s1.charAt(index2)) {
                    index2++;
                }
                index1++;
            }
            if (index2 == s1.length()&&(s1.length() > res.length()
                    ||(s1.length() == res.length()&&s1.compareTo(res)<0))) {
                res = s1;
            }
        }
        return res;
    }

}
