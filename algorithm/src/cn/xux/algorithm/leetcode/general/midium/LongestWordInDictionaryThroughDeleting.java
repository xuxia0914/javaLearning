package cn.xux.algorithm.leetcode.general.midium;

import java.util.List;

/**
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 */
public class LongestWordInDictionaryThroughDeleting {

    public String findLongestWord(String s, List<String> d) {
        String res = "";
        if (s == null || s.length() == 0 || d == null || d.size() == 0) {
            return res;
        }
        int index1, index2;
        for (String s1 : d) {
            index1 = 0;
            index2 = 0;
            while (index1 < s.length() && index2 < s1.length()) {
                if (s.charAt(index1) == s1.charAt(index2)) {
                    index1++;
                    index2++;
                } else {
                    index1++;
                }
            }
            if (index2 == s1.length()) {
                if (s1.length() > res.length()) {
                    res = s1;
                } else if (s1.length() == res.length()) {
                    for (int i = 0; i < s1.length(); i++) {
                        if (s1.charAt(i) > res.charAt(i)) {
                            break;
                        } else if (s1.charAt(i) < res.charAt(i)) {
                            res = s1;
                            break;
                        }
                    }
                }

            }
        }
        return res;
    }

}
