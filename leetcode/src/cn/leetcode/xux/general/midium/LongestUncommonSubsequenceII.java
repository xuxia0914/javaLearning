package cn.leetcode.xux.general.midium;

/**
 * Given a list of strings, you need to find the longest uncommon subsequence among them. The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.
 * The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence. If the longest uncommon subsequence doesn't exist, return -1.
 * Example 1:
 * Input: "aba", "cdc", "eae"
 * Output: 3
 * Note:
 * All the given strings' lengths will not exceed 10.
 * The length of the given list will be in the range of [2, 50].
 */
public class LongestUncommonSubsequenceII {

    public int findLUSlength(String[] strs) {
        if(strs==null||strs.length<2) {
            return -1;
        }
        String tmp;
        int res = -1;
        for(int i=0;i<strs.length;i++) {
            tmp = strs[i];
            int j;
            for(j=0;j<strs.length;j++) {
                if(j==i) {
                    continue;
                }
                if(checkSubs(tmp, strs[j])) {
                    break;
                }
            }
            if(j==strs.length) {
                res = Math.max(res, tmp.length());
            }
        }
        return res;
    }

    boolean checkSubs(String subs, String str) {
        int i = 0;
        int j = 0;
        while(i<subs.length()&&j<str.length()) {
            if(subs.charAt(i)==str.charAt(j)) {
                i++;
                j++;
            }else {
                j++;
            }
        }
        if(i==subs.length()) {
            return true;
        }else {
            return false;
        }
    }

}
