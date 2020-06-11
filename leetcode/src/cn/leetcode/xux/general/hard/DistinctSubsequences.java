package cn.leetcode.xux.general.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 115. 不同的子序列
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）
 * 字符且不干扰剩余字符相对位置所组成的新字符串。
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 * 示例 1：
 * 输入：S = "rabbbit", T = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 *
 * 示例 2：
 * 输入：S = "babgbag", T = "bag"
 * 输出：5
 *
 * 解释：
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 */
public class DistinctSubsequences {

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequences().numDistinct("rabbbit", "rabbit"));
    }

    public int numDistinct(String s, String t) {
        int lenT = t.length();
        List<Integer>[] cnts = new List[58];
        for(int i=0;i<lenT;i++) {
            char c = t.charAt(i);
            if(cnts[c-'A']==null) {
                cnts[c-'A'] = new ArrayList<>();
            }
            cnts[c-'A'].add(i);
        }
        int[] dp = new int[lenT+1];
        dp[0] = 1;
        for(char c : s.toCharArray()) {
            if(cnts[c-'A']!=null) {
                List<Integer> list = cnts[c-'A'];
                for(int i=list.size()-1;i>=0;i--) {
                    dp[list.get(i)+1] += dp[list.get(i)];
                }
            }
        }
        return dp[lenT];
    }

}
