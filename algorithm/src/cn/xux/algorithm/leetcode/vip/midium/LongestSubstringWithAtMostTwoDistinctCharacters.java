package cn.xux.algorithm.leetcode.vip.midium;

/**
 * 159. 至多包含两个不同字符的最长子串
 *
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 *
 * 示例 1:
 *
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 *
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters l = new LongestSubstringWithAtMostTwoDistinctCharacters();
        System.out.println(l.lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(l.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        int n = s.length();
        if(n<3) {
            return n;
        }
        int left = 0;
        int[] cnts = new int[26];
        cnts[s.charAt(0)-'a']++;
        int kind = 1;
        int ans = 2;
        for(int right=1;right<n;right++) {
            int rc = s.charAt(right)-'a';
            if(cnts[rc]++==0) {
                kind++;
            }
            while(kind>2) {
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
