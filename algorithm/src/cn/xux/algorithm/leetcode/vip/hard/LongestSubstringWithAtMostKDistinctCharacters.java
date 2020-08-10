package cn.xux.algorithm.leetcode.vip.hard;

/**
 * 340.至多包含 K 个不同字符的最长子串
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 *
 * 示例 1:
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 *
 * 示例 2:
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 */
public class LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s==null||s.length()==0||k<1) {
            return 0;
        }
        int n = s.length();
        if(n<=k) {
            return n;
        }
        int left = 0;
        int[] cnts = new int[26];
        int kind = 0;
        int ans = k;
        for(int right=0;right<n;right++) {
            int rc = s.charAt(right)-'a';
            if(cnts[rc]++==0) {
                kind++;
            }
            while(kind>k) {
                int lc = s.charAt(left++)-'a';
                if(cnts[lc]--==1) {
                    kind--;
                }
            }
            ans = Math.max(ans, right-left+1);
        }
        return ans;
    }

}
