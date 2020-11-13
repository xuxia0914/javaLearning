package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1638. 统计只差一个字符的子串数目
 * 给你两个字符串 s 和 t ，请你找出 s 中的非空子串的数目，
 * 这些子串满足替换 一个不同字符 以后，是 t 串的子串。
 * 换言之，请你找到 s 和 t 串中 恰好 只有一个字符不同的子字符串对的数目。
 * 比方说， "computer" 和 "computation" 加粗部分只有一个字符不同： 'e'/'a' ，
 * 所以这一对子字符串会给答案加 1 。
 * 请你返回满足上述条件的不同子字符串对数目。
 * 一个 子字符串 是一个字符串中连续的字符。
 *
 * 示例 1：
 * 输入：s = "aba", t = "baba"
 * 输出：6
 * 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * 加粗部分分别表示 s 和 t 串选出来的子字符串。
 *
 * 示例 2：
 * 输入：s = "ab", t = "bb"
 * 输出：3
 * 解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
 * ("ab", "bb")
 * ("ab", "bb")
 * ("ab", "bb")
 * 加粗部分分别表示 s 和 t 串选出来的子字符串。
 *
 * 示例 3：
 * 输入：s = "a", t = "a"
 * 输出：0
 *
 * 示例 4：
 * 输入：s = "abe", t = "bbc"
 * 输出：10
 *
 * 提示：
 * 1 <= s.length, t.length <= 100
 * s 和 t 都只包含小写英文字母。
 */
public class CountSubstringsThatDifferByOneCharacter {

    public int countSubstrings(String s, String t) {
         int n1 = s.length();
         int n2 = t.length();
         // f[i][j]表示以s[i-1]和t[j-1]为结束的只差一个字符的子串数目
         int[][] f = new int[n1+1][n2+1];
        // g[i][j]表示以s[i-1]和t[j-1]为结束的完全一样的子串数目
         int[][] g = new int[n1+1][n2+1];
         int ans = 0;
         for(int i=1;i<=n1;i++) {
             for(int j=1;j<=n2;j++) {
                 if(s.charAt(i-1)==t.charAt(j-1)) {
                     f[i][j] = f[i-1][j-1];
                     g[i][j] = g[i-1][j-1]+1;
                 }else {
                     f[i][j] = g[i-1][j-1]+1;
                     g[i][j] = 0;
                 }
                 ans += f[i][j];
             }
         }
         return ans;
    }

    // 空间可以优化,以对角线遍历的方式，f、g的值可以迭代
    public int countSubstrings1(String s, String t) {
        int n1 = s.length();
        int n2 = t.length();
        int ans = 0;
        for(int i=0;i<n1;i++) {
            int f = 0;
            int g = 0;
            for(int j=0;j<n2&&i+j<n1;j++) {
                if(s.charAt(i+j)==t.charAt(j)) {
                    g++;
                }else {
                    f = g+1;
                    g = 0;
                }
                ans += f;
            }
        }
        for(int j=1;j<n2;j++) {
            int f = 0;
            int g = 0;
            for(int i=0;i<n1&&j+i<n2;i++) {
                if(t.charAt(j+i)==s.charAt(i)) {
                    g++;
                }else {
                    f = g+1;
                    g = 0;
                }
                ans += f;
            }
        }
        return ans;
    }

}
