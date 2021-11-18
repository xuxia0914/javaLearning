package cn.xux.algorithm.leetcode.general.midium;

/**
 * 318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，
 * 并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。
 * 如果不存在这样的两个单词，返回 0。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 * <p>
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * <p>
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 */
public class MaximumProductOfWordLengths {

    public int maxProduct(String[] words) {
        int n = words.length;
        int[] states = new int[n];
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                states[i] |= 1 << (c - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((states[i] & states[j]) == 0) {
                    ans = Math.max(ans, words[i].length() * words[j].length());
                }
            }
        }
        return ans;
    }

    public int maxProduct1(String[] words) {
        if (words == null || words.length < 2) {
            return 0;
        }
        int n = words.length;
        boolean[][] flags = new boolean[n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                flags[i][words[i].charAt(j) - 'a'] = true;
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (words[i].length() * words[j].length() <= res) {
                    continue;
                }
                boolean flag = false;
                for (int k = 0; k < 26; k++) {
                    if (flags[i][k] && flags[j][k]) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }

}
