package cn.xux.algorithm.leetcode.general.midium;

/**
 * 1653. 使字符串平衡的最少删除次数
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。
 * 我们称 s 平衡的 当不存在下标对 (i,j) 满足 i < j 且 s[i] = 'b' 同时 s[j]= 'a' 。
 * 请你返回使 s 平衡 的 最少 删除次数。
 *
 * 示例 1：
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 *
 * 示例 2：
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 *
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 要么是 'a' 要么是 'b'​ 。​
 */
public class MinimumDeletionsToMakeStringBalanced {

    public int minimumDeletions(String s) {
        int n = s.length();
        // preBCnt[i]表示s[i]前面的b字符的数量
        int[] preBCnt = new int[n];
        // postACnt[i]表示s[i]后面的a字符的数量
        int[] postACnt = new int[n];
        for(int i=1;i<n;i++) {
            preBCnt[i] = preBCnt[i-1];
            if(s.charAt(i-1)=='b') {
                preBCnt[i]++;
            }
            postACnt[n-i-1] = postACnt[n-i];
            if(s.charAt(n-i)=='a') {
                postACnt[n-i-1]++;
            }
        }
        int ans = n;
        for(int i=0;i<n;i++) {
            ans = Math.min(ans, preBCnt[i]+postACnt[i]);
        }
        return ans;
    }

}
