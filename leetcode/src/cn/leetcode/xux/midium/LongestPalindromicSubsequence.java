package cn.leetcode.xux.midium;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * Example 1:
 * Input:
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {

    /**Time Limit Exceeded 复杂度过高*/
    public int solution1(String s) {
        if(s==null) {
            return 0;
        }
        int len = s.length();
        if(len<2) {
            return len;
        }
        if(s.charAt(0)==s.charAt(len-1)) {
            return 2+solution1(s.substring(1, len-1));
        }else {
            return Math.max(solution1(s.substring(1)), solution1(s.substring(0, len-1)));
        }
    }

    /**
     * 我们建立一个二维的DP数组，其中dp[i][j]表示[i,j]区间内的字符串的最长回文子序列，那么对于递推公式我们分析一下，如果s[i]==s[j]，那么i和j就可以增加2个回文串的长度，我们知道中间dp[i + 1][j - 1]的值，那么其加上2就是dp[i][j]的值。如果s[i] != s[j]，那么我们可以去掉i或j其中的一个字符，然后比较两种情况下所剩的字符串谁dp值大，就赋给dp[i][j]，那么递推公式如下：
     *               /  dp[i + 1][j - 1] + 2                       if (s[i] == s[j])
     *
     * dp[i][j] =
     *
     *               \  max(dp[i + 1][j], dp[i][j - 1])        if (s[i] != s[j])
     * @param s
     * @return
     */
    public static int solution2(String s) {
        if(s==null) {
            return 0;
        }
        char[] ss = s.toCharArray();
        int n = ss.length;
        if(n<2) {
            return n;
        }
        int[][] dp = new int[n][n];
        for(int i=n-1;i>=0;i--) {
            dp[i][i] = 1;
            for(int j=i+1;j<n;j++) {
                if(ss[i]==ss[j]) {
                    dp[i][j] = dp[i+1][j-1]+2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
        //输出：159
        System.out.println(solution2("euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew"));
    }

}
