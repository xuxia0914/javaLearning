package cn.xux.algorithm.leetcode.general.easy;

/**
 * Given a string and an integer k,
 * you need to reverse the first k characters for every 2k characters counting from the start of the string.
 * If there are less than k characters left, reverse all of them.
 * If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and left the other as original.
 * Example:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * Restrictions:
 * The string consists of lower English letters only.
 * Length of the given string and k will in the range [1, 10000]
 */
public class ReverseStringII {

    public String reverseStr(String s, int k) {
        if(s==null||s.length()<3||k<2) {
            return s;
        }
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<len;i+=2*k) {
            for(int j=Math.min(i+k-1,len-1);j>=i;j--) {
                sb.append(s.charAt(j));
            }
            for(int j=i+k;j<i+2*k&&j<len;j++) {
                sb.append(s.charAt(j));
            }
        }
        return sb.toString();
    }

}
