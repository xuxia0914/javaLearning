package cn.xux.algorithm.leetcode.general.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 87. 扰乱字符串
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 *
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 *
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 *
 * 提示：
 * s1.length == s2.length
 * 1 <= s1.length <= 30
 * s1 和 s2 由小写英文字母组成
 */
public class ScrambleString {

    public static void main(String[] args) {
        System.out.println(new ScrambleString().isScramble("abba", "abab"));
    }

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        // dp[l1][l2][n] 表示s1(l1起始)和s2(l2起始)的长度为n的子串是不是扰乱字符串
        boolean[][][] dp = new boolean[n][n][n+1];
        // len==1时
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j][1] = s1.charAt(i)==s2.charAt(j);
            }
        }
        // 长度
        for(int l=2;l<=n;l++) {
            // s1起点
            for(int i=0;i+l-1<n;i++) {
                // s2起点
                for(int j=0;j+l-1<n;j++) {
                    // s1左边子串的长度
                    for(int ll=1;ll<l;ll++) {
                        if((dp[i][j][ll]&&dp[i+ll][j+ll][l-ll])
                                ||(dp[i][j+l-ll][ll]&&dp[i+ll][j][l-ll])) {
                            dp[i][j][l] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }

    Map<String, Boolean> mem = new HashMap<>();

    public boolean isScramble1(String s1, String s2) {
        if(s1.length()!=s2.length()) {
            return false;
        }
        if(s1.equals(s2)) {
            return true;
        }
        String key = s1+"#"+s2;
        if(mem.containsKey(key)) {
            return mem.get(key);
        }
        int[] cnts = new int[26];
        for(char c : s1.toCharArray()) {
            cnts[c-'a']++;
        }
        for(char c : s1.toCharArray()) {
            if(--cnts[c-'a']<0) {
                mem.put(key, false);
                return false;
            }
        }
        for(int cnt : cnts) {
            if(cnt!=0) {
                mem.put(key, false);
                return false;
            }
        }
        int len = s1.length();
        for(int i=1;i<len;i++) {
            if(isScramble(s1.substring(0,i), s2.substring(0, i))
                    &&isScramble(s1.substring(i), s2.substring(i))) {
                mem.put(key, true);
                return true;
            }
            if(isScramble(s1.substring(0,i), s2.substring(len-i))
                    &&isScramble(s1.substring(i), s2.substring(0, len-i))) {
                mem.put(key, true);
                return true;
            }
        }
        mem.put(key, false);
        return false;
    }

}
