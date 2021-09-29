package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1930. 长度为 3 的不同回文子序列
 * 给你一个字符串 s ，返回 s 中 长度为 3 的不同回文子序列 的个数。
 * 即便存在多种方法来构建相同的子序列，但相同的子序列只计数一次。
 * 回文 是正着读和反着读一样的字符串。
 * 子序列 是由原字符串删除其中部分字符（也可以不删除）且不改变剩余字符之间相对顺序形成的一个新字符串。
 * 例如，"ace" 是 "abcde" 的一个子序列。
 *
 * 示例 1：
 * 输入：s = "aabca"
 * 输出：3
 * 解释：长度为 3 的 3 个回文子序列分别是：
 * - "aba" ("aabca" 的子序列)
 * - "aaa" ("aabca" 的子序列)
 * - "aca" ("aabca" 的子序列)
 *
 * 示例 2：
 * 输入：s = "adc"
 * 输出：0
 * 解释："adc" 不存在长度为 3 的回文子序列。
 *
 * 示例 3：
 * 输入：s = "bbcbaba"
 * 输出：4
 * 解释：长度为 3 的 4 个回文子序列分别是：
 * - "bbb" ("bbcbaba" 的子序列)
 * - "bcb" ("bbcbaba" 的子序列)
 * - "bab" ("bbcbaba" 的子序列)
 * - "aba" ("bbcbaba" 的子序列)
 *
 * 提示：
 * 3 <= s.length <= 105
 * s 仅由小写英文字母组成
 */
public class UniqueLength3PalindromicSubsequences {

    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        boolean[] visited = new boolean[26];
        int ans = 0;
        for(int i=0;i<n-2;i++) {
            char c = s.charAt(i);
            if(!visited[c-'a']) {
                visited[c-'a'] = true;
                for(int j=n-1;j>i+1;j--) {
                    if(s.charAt(j)==c) {
                        boolean[] cnts = new boolean[26];
                        int tmp = 0;
                        for(int k=i+1;k<j;k++) {
                            if(!cnts[s.charAt(k)-'a']) {
                                cnts[s.charAt(k)-'a'] = true;
                                tmp++;
                            }
                        }
                        ans += tmp;
                        break;
                    }
                }
            }
        }
        return ans;
    }

}
