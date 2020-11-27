package cn.xux.algorithm.leetcode.general.easy;

/**
 * 1662. 检查两个字符串数组是否相等
 * 给你两个字符串数组 word1 和 word2 。
 * 如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
 * 数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。
 *
 * 示例 1：
 * 输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
 * 输出：true
 * 解释：
 * word1 表示的字符串为 "ab" + "c" -> "abc"
 * word2 表示的字符串为 "a" + "bc" -> "abc"
 * 两个字符串相同，返回 true
 *
 * 示例 2：
 * 输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
 * 输出：false
 *
 * 示例 3：
 * 输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * 输出：true
 *
 * 提示：
 * 1 <= word1.length, word2.length <= 103
 * 1 <= word1[i].length, word2[i].length <= 103
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
 * word1[i] 和 word2[i] 由小写字母组成
 */
public class CheckIfTwoStringArraysAreEquivalent {

    public static void main(String[] args) {
        System.out.println(new CheckIfTwoStringArraysAreEquivalent()
                .arrayStringsAreEqual(new String[]{"a","cb"}, new String[]{"ac","b"}));
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int n1 = word1.length;
        int n2 = word2.length;
        // pos1表示当前遍历到word1[pos1[0]].charAt(pos1[1])
        int[] pos1 = new int[2];
        int[] pos2 = new int[2];
        while(pos1[0]<n1&&pos2[0]<n2) {
            if(word1[pos1[0]].charAt(pos1[1])==word2[pos2[0]].charAt(pos2[1])) {
                pos1[1]++;
                pos2[1]++;
                if(pos1[1]==word1[pos1[0]].length()) {
                    pos1[0]++;
                    pos1[1] = 0;
                }
                if(pos2[1]==word2[pos2[0]].length()) {
                    pos2[0]++;
                    pos2[1] = 0;
                }
            }else {
                return false;
            }
        }
        return pos1[0]==n1&&pos2[0]==n2;
    }

}
