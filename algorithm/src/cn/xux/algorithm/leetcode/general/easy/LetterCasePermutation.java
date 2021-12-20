package cn.xux.algorithm.leetcode.general.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. 字母大小写全排列
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * <p>
 * <p>
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 * <p>
 * 输入：S = "12345"
 * 输出：["12345"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */
public class LetterCasePermutation {

    List<String> ans;

    String s;

    public List<String> letterCasePermutation(String s) {
        ans = new ArrayList<>();
        this.s = s;
        dfs("", 0);
        return ans;
    }

    private void dfs(String curr, int i) {
        if (i == s.length()) {
            ans.add(curr);
            return;
        }
        char c = s.charAt(i);
        dfs(curr + c, i + 1);
        if (c >= 'a' && c <= 'z') {
            dfs(curr + (char) (c - 32), i + 1);
        } else if (c >= 'A' && c <= 'Z') {
            dfs(curr + (char) (c + 32), i + 1);
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation lcp = new LetterCasePermutation();
        System.out.println(lcp.letterCasePermutation("a1b2"));
    }

}
