package cn.xux.algorithm.leetcode.general.midium;

/**
 * 2131. 连接两字母单词得到的最长回文串
 * 给你一个字符串数组 words 。
 * words 中每个元素都是一个包含 两个 小写英文字母的单词。
 * <p>
 * 请你从 words 中选择一些元素并按 任意顺序 连接它们，
 * 并得到一个 尽可能长的回文串 。每个元素 至多 只能使用一次。
 * <p>
 * 请你返回你能得到的最长回文串的 长度 。
 * 如果没办法得到任何一个回文串，请你返回 0 。
 * <p>
 * 回文串 指的是从前往后和从后往前读一样的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["lc","cl","gg"]
 * 输出：6
 * 解释：一个最长的回文串为 "lc" + "gg" + "cl" = "lcggcl" ，长度为 6 。
 * "clgglc" 是另一个可以得到的最长回文串。
 * 示例 2：
 * <p>
 * 输入：words = ["ab","ty","yt","lc","cl","ab"]
 * 输出：8
 * 解释：最长回文串是 "ty" + "lc" + "cl" + "yt" = "tylcclyt" ，长度为 8 。
 * "lcyttycl" 是另一个可以得到的最长回文串。
 * 示例 3：
 * <p>
 * 输入：words = ["cc","ll","xx"]
 * 输出：2
 * 解释：最长回文串是 "cc" ，长度为 2 。
 * "ll" 是另一个可以得到的最长回文串。"xx" 也是。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 105
 * words[i].length == 2
 * words[i] 仅包含小写英文字母。
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {

    public static void main(String[] args) {
        System.out.println(new LongestPalindromeByConcatenatingTwoLetterWords()
                .longestPalindrome(new String[]{
                        "qo", "fo", "fq", "qf", "fo", "ff", "qq", "qf",
                        "of", "of", "oo", "of", "of", "qf", "qf", "of"}));
    }

    public int longestPalindrome(String[] words) {
        int[] cnt = new int[26 * 26];
        int ans = 0;
        for (String word : words) {
            int curr = ((word.charAt(0) - 'a') * 26) + word.charAt(1) - 'a';
            int reverse = ((word.charAt(1) - 'a') * 26) + word.charAt(0) - 'a';
            if (cnt[reverse] > 0) {
                ans += 4;
                cnt[reverse]--;
            } else {
                cnt[curr]++;
            }
        }
        for (int i = 0; i < cnt.length; i += 27) {
            if (cnt[i] > 0) {
                ans += 2;
                break;
            }
        }
        return ans;
    }

}
